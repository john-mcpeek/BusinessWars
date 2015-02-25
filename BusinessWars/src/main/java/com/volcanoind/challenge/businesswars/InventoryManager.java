package com.volcanoind.challenge.businesswars;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.volcanoind.challenge.businesswars.domain.LineItem;
import com.volcanoind.challenge.businesswars.domain.Product;
import com.volcanoind.challenge.businesswars.domain.Transaction;
import com.volcanoind.challenge.businesswars.domain.TxLineItem;
import com.volcanoind.challenge.businesswars.domain.TxRequest;
import com.volcanoind.challenge.businesswars.exceptions.ProductNotFoundException;

@Component
public class InventoryManager {
	protected Map<String, Product> products = new HashMap<String, Product>();

	protected Map<String, LineItem> stockOnHand = new HashMap<String, LineItem>();

	protected List<Transaction> history = Collections.synchronizedList( new ArrayList<Transaction>() );

	@Value( "${products.data.path}" )
	protected String dataPath;

	@PostConstruct
	public void init() throws IOException {
		Reader reader = new FileReader( dataPath );
		try ( CSVParser parser = new CSVParser( reader, CSVFormat.DEFAULT.withHeader() ) ) {
			Iterable<CSVRecord> records = parser.getRecords();
			for ( CSVRecord record : records ) {
				String sku = record.get( "sku" );
				String name = record.get( "name" );
				String price = record.get( "price" );
				String qty = record.get( "qty" );

				Product product = new Product( sku, name, price );
				LineItem lineItem = new LineItem( product, qty );

				stockOnHand.put( sku, lineItem );
				products.put( sku, product );
			}
		}
	}

	public Collection<Product> getProducts() {
		return products.values();
	}

	public Collection<LineItem> getStock() {
		return stockOnHand.values();
	}

	public Product getStock(String sku) {
		LineItem lineItem = stockOnHand.get( sku );

		if ( lineItem == null ) {
			throw new ProductNotFoundException();
		}

		return lineItem;
	}

	public Transaction buyItem(TxRequest bid) {
		boolean allOrNone = bid.isAllOrNone();

		Transaction tx = new Transaction( UUID.randomUUID().toString() );

		bid.getLineItems().forEach( bidLine -> {
			LineItem lineItem = stockOnHand.get( bidLine.getSku() );

			if ( lineItem == null ) {
				throw new ProductNotFoundException();
			}

			TxLineItem txLineItem = new TxLineItem( bidLine );
			tx.addLineItem( txLineItem );

			synchronized ( lineItem ) {
				int qty = lineItem.getQty();

				BigDecimal bidPrice = bidLine.getPrice();
				int bidRequest = bidLine.getQty();

				boolean pricesMisMatch = lineItem.getPrice().compareTo( bidPrice ) != 0;
				if ( pricesMisMatch ) {
					txLineItem.reject();
					return;
				}

				boolean hasEnough = ( qty >= bidRequest );
				if ( hasEnough ) {
					lineItem.setQty( qty - bidRequest );
					txLineItem.setQty( bidRequest );
				}
				else {
					if ( allOrNone ) {
						txLineItem.reject();
						return;
					}
					else {
						lineItem.setQty( 0 );
						txLineItem.setQty( qty );
					}

				}
			}
		} );

		history.add( tx );

		return tx;
	}

	public List<Transaction> getSalesHistory() {
		return history;
	}
}

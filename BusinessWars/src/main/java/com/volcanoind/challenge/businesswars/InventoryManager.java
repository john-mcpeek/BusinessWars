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

import com.volcanoind.challenge.businesswars.domain.Bid;
import com.volcanoind.challenge.businesswars.domain.Purchase;
import com.volcanoind.challenge.businesswars.domain.StockAvailable;
import com.volcanoind.challenge.businesswars.exceptions.PriceMismatchException;
import com.volcanoind.challenge.businesswars.exceptions.ProductNotFoundException;

@Component
public class InventoryManager {
	private Map<String, StockAvailable> stockOnHand = new HashMap<String, StockAvailable>();
	
	private List<Purchase> history = Collections.synchronizedList( new ArrayList<Purchase>() );

	@Value( "${products.data.path}" )
	private String dataPath;

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

				StockAvailable stockItem = new StockAvailable( sku, name, price, qty );
				stockOnHand.put( stockItem.getSku(), stockItem );
			}
		}
	}

	public Collection<StockAvailable> getProducts() {
		return stockOnHand.values();
	}

	public StockAvailable getQuote(String sku) {
		StockAvailable stock = stockOnHand.get( sku );
		
		if ( stock == null ) {
			throw new ProductNotFoundException();
		}
		
		return stock;
	}

	public Purchase buyItem(Bid bid) {
		StockAvailable stock = stockOnHand.get( bid.getSku() );
		
		if ( stock == null ) {
			throw new ProductNotFoundException();
		}

		synchronized ( stock ) {
			BigDecimal price = bid.getPrice();
			if ( stock.getPrice().compareTo( price ) != 0 ) {
				throw new PriceMismatchException( price, stock.getPrice() );
			}

			int takenFromStock = stock.takeFromStock( bid );
			Purchase puchase = new Purchase( stock, takenFromStock, UUID.randomUUID().toString() );
			history.add( puchase );

			return puchase;
		}
	}
	
	public List<Purchase> getSalesHistory() {
		return history;
	}
}

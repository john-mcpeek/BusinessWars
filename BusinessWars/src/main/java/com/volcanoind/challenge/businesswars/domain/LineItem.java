package com.volcanoind.challenge.businesswars.domain;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.volcanoind.challenge.businesswars.exceptions.InsufficientStockException;

@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
public class LineItem extends Product {
	
	@JsonProperty( "quantity" )
	private int qty;

	private BigDecimal subtotal;

	public LineItem(Product product, String quantity) {
		super( product.getSku(), product.getName(), product.getPrice() );
	}

	public LineItem(LineItem lineItem) {
		this( lineItem.getSku(), lineItem.getName(), lineItem.getPrice(), lineItem.getQty(), lineItem.getSubtotal() );
	}

	public LineItem(String sku, String name, BigDecimal price, int qty, BigDecimal subtotal) {
		super( sku, name, price );
		this.qty = qty;
		this.subtotal = subtotal;
	}

	public int takeFromStock(LineItem bid, boolean allOrNone) {
		int requested = bid.getQty();
		boolean hasEnough = ( qty >= requested );

		int taken = 0;
		if ( hasEnough ) {
			qty -= requested;
			taken = requested;
		}
		else {
			if ( allOrNone ) {
				throw new InsufficientStockException( requested, qty );
			}
			else {
				taken = qty;
				qty = 0;
			}
		}

		return taken;
	}
}

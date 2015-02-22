package com.volcanoind.challenge.businesswars.domain;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.volcanoind.challenge.businesswars.exceptions.InsufficientStockException;

@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
@ToString(callSuper=true, includeFieldNames=true)
public class Product extends Item {
	protected int qty;

	public Product(String sku, String name, String price, String qty) {
		this.sku = sku;
		this.name = name;
		this.price = new BigDecimal( price );
		this.qty = Integer.parseInt( qty );
	}

	public Product(String sku, String name, BigDecimal price, int qty) {
		super( sku, name, price );

		this.qty = qty;
	}

	public int takeFromStock(Bid bid) {
		int requested = bid.getQty();
		boolean hasEnough = ( qty >= requested );

		int taken = 0;
		if ( hasEnough ) {
			qty -= requested;
			taken = requested;
		}
		else {
			if ( bid.isAllOrNone() ) {
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

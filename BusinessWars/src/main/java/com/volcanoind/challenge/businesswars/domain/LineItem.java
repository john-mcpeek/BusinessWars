package com.volcanoind.challenge.businesswars.domain;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
public class LineItem extends Product {

	@Min( 0 )
	@JsonProperty( "quantity" )
	protected int qty;

	@DecimalMin( "0" )
	protected BigDecimal subtotal;

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
}

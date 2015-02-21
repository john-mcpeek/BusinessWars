package com.volcanoind.challenge.businesswars.domain;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
public class Purchase extends Item {
	@JsonProperty( "qty-purchased" )
	private int qtyPurchased;

	private BigDecimal total;

	@JsonProperty( "trans-id" )
	private String transId;

	public Purchase(Product product, int qtyPurchased, String transId) {
		super( product.getSku(), product.getName(), product.getPrice() );

		this.qtyPurchased = qtyPurchased;
		this.transId = transId;

		this.total = product.getPrice().multiply( new BigDecimal( qtyPurchased ) );
	}
}

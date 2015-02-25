package com.volcanoind.challenge.businesswars.domain;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@NotNull
	@Size( min = 1, max = 64 )
	protected String sku;

	@Size( max = 128 )
	protected String name;

	@DecimalMin( "0" )
	protected BigDecimal price;

	public Product(String sku, String name, String price) {
		this.sku = sku;
		this.name = name;
		this.price = new BigDecimal( price );
	}
}

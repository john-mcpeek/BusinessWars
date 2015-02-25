package com.volcanoind.challenge.businesswars.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	protected String sku;

	protected String name;

	protected BigDecimal price;

	public Product(String sku, String name, String price) {
		this.sku = sku;
		this.name = name;
		this.price = new BigDecimal( price );
	}
}

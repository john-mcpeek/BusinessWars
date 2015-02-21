package com.volcanoind.challenge.businesswars.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	protected String sku;

	protected String name;

	protected BigDecimal price;
}

package com.volcanoind.challenge.businesswars.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bid {
	private String sku;

	private int qty;

	private BigDecimal price;

	@JsonProperty( "all-or-none" )
	private boolean allOrNone;
}

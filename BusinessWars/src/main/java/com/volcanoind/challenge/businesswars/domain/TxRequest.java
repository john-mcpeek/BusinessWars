package com.volcanoind.challenge.businesswars.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TxRequest {

	@JsonProperty( "all-or-none" )
	private boolean allOrNone;
	
	private BigDecimal total;
	
	private List<LineItem> lineItems = new ArrayList<LineItem>();
}

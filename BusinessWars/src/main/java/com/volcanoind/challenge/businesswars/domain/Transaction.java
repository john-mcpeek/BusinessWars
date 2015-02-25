package com.volcanoind.challenge.businesswars.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
public class Transaction {

	@JsonProperty( "trans-id" )
	private String transId;
	
	private BigDecimal total;

	private List<TxLineItem> lineItems = new ArrayList<TxLineItem>();
	
	public Transaction(String transId) {
		this.transId = transId;
	}
	
	public void addLineItem(TxLineItem item) {
		lineItems.add( item );
	}
}

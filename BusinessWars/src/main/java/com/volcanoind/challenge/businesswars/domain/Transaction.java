package com.volcanoind.challenge.businesswars.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.DecimalMin;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
public class Transaction {
	
	@NotBlank
	@JsonProperty( "trans-id" )
	private String transId;

	@DecimalMin( "0" )
	private BigDecimal total;

	private List<TxLineItem> lineItems = new ArrayList<TxLineItem>();
	
	public Transaction(String transId) {
		this.transId = transId;
	}
	
	public void addLineItem(TxLineItem item) {
		lineItems.add( item );
	}
}

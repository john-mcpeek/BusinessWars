package com.volcanoind.challenge.businesswars.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( callSuper = true )
public class TxLineItem extends LineItem {
	
	private boolean accepted;
	
	public TxLineItem(LineItem bidItem) {
		super( bidItem );
		
		accepted = true;
	}
	
	public void reject() {
		accepted = false;
		qty = 0;
		price = BigDecimal.ZERO;
		subtotal = BigDecimal.ZERO;
	}
}

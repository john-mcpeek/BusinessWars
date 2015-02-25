package com.volcanoind.challenge.businesswars.domain;

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
	
	public TxLineItem(LineItem bidItem, boolean accepted) {
		super( bidItem );
		
		this.accepted = accepted;
	}
}

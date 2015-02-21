package com.volcanoind.challenge.businesswars.exceptions;

import org.springframework.http.HttpStatus;

public class InsufficientStockException extends ResponseCodeException {
	
	public InsufficientStockException(int requested, int available) {
		super( HttpStatus.BAD_REQUEST );
		setMessage( "Insufficient Stock to fulfill request. Available: " + available + ", Requested: " + requested );
	}
}
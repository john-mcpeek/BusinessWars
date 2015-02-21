package com.volcanoind.challenge.businesswars.exceptions;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;

public class PriceMismatchException extends ResponseCodeException {
	
	public PriceMismatchException(BigDecimal yours, BigDecimal mine) {
		super( HttpStatus.BAD_REQUEST );
		setMessage( "Price mismatch. Mine: " + mine + ", Yours: " + yours );
	}
}

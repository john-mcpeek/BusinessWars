package com.volcanoind.challenge.businesswars.exceptions;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ResponseCodeException {
	
	public ProductNotFoundException() {
		super( HttpStatus.NOT_FOUND, "We don't sell that sort of deviant paraphernalia around here. You sicko!" );
	}
}
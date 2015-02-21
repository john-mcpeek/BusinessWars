package com.volcanoind.challenge.businesswars.exceptions;

import org.springframework.http.HttpStatus;

public class ResponseCodeException extends RuntimeException {
	private HttpStatus status;
	private String message;
	
	public ResponseCodeException(HttpStatus status) {
		this.status = status;
	}
	
	public ResponseCodeException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	protected void setMessage(String message) {
		this.message = message;
	}
}

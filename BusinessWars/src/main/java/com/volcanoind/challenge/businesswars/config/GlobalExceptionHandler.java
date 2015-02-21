package com.volcanoind.challenge.businesswars.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.volcanoind.challenge.businesswars.exceptions.ResponseCodeException;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger( GlobalExceptionHandler.class );

	@ExceptionHandler( Throwable.class )
	public ResponseEntity<String> handleGlobalException(Throwable e) throws Throwable {
		// If the exception is annotated with @ResponseStatus re-throw it and let the framework handle it.
		// AnnotationUtils is a Spring Framework utility class.
		if ( AnnotationUtils.findAnnotation( e.getClass(), ResponseStatus.class ) != null ) {
			throw e;
		}

		if ( e instanceof ResponseCodeException ) {
			return new ResponseEntity<>( e.getMessage(), ( (ResponseCodeException) e ).getStatus() );
		}
		else {
			log.debug( "", e );
			return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
		}

	}

}

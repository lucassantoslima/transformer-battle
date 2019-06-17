package com.aequilibrium.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.CONFLICT)//409
public class DuplicateItemException extends APIException {
	
	private static final long serialVersionUID = 1L;
	
	public DuplicateItemException(final String msg) {
		super(HttpStatus.CONFLICT, msg);
	}
	
	public DuplicateItemException(final String msg, final Object[] parameters) {
		super(HttpStatus.CONFLICT, msg, parameters);
	}
	
}

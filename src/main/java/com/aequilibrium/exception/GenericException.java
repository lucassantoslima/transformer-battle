package com.aequilibrium.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class GenericException extends APIException {

	private static final long serialVersionUID = 1L;
	
	public GenericException(final String msg) {
		super(HttpStatus.CONFLICT, msg);
	}
	
	public GenericException(final String msg, final Object[] parameters) {
		super(HttpStatus.CONFLICT, msg, parameters);
	}

}

package com.aequilibrium.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)//404
public class NotFoundException extends APIException {

	private static final long serialVersionUID = 1L;
	
	public NotFoundException(final String msg) {
		super(HttpStatus.NOT_FOUND, msg);
	}
	
	public NotFoundException(final String msg, final Object[] parameters) {
		super(HttpStatus.NOT_FOUND, msg, parameters);
	}
	
}

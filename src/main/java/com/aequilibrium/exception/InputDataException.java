package com.aequilibrium.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InputDataException extends APIException {

	private static final long serialVersionUID = 1L;
	
	public InputDataException(final String msg) {
		super(HttpStatus.CONFLICT, msg);
	}
	
	public InputDataException(final String msg, final Object[] parameters) {
		super(HttpStatus.CONFLICT, msg, parameters);
	}

}

package com.aequilibrium.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public abstract class APIException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final HttpStatus status;
	private Object[] parameters;

	public APIException(final HttpStatus status, final String message) {
		super(message);
		if (status == null) {
			throw new IllegalArgumentException();
		}
		this.status = status;
	}
	
	public APIException(final HttpStatus status, final String message, final Object[] parameters) {
		super(message);
		if (status == null) {
			throw new IllegalArgumentException();
		}
		this.status = status;
		this.parameters = parameters;
	}

}

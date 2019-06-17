package com.aequilibrium.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class ErrorMessageDetails {
	
	private String field;
	
	private String message;

	public ErrorMessageDetails(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}
	

}

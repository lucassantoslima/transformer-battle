package com.aequilibrium.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.aequilibrium.exception.APIException;
import com.aequilibrium.exception.ErrorCodes;
import com.aequilibrium.exception.ErrorMessage;
import com.aequilibrium.exception.ErrorMessageDetails;


@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	private MessageSource messageSource;

	@Autowired
	public AppExceptionHandler(final MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	protected void processException(final Exception ex) {
		ex.printStackTrace();
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		return handleExceptionInternal(ex, createErrorMessage(status, ex.getMessage(), null), headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		return handleExceptionInternal(ex, createErrorMessage(status, ErrorCodes.VALIDATE_FIELD_INVALID_INPUT, ex.getBindingResult().getFieldErrors()), headers, status, request);
	}
	
	@ExceptionHandler(APIException.class) 
	public ResponseEntity<?> handleAPIException(final APIException ex, final WebRequest request) {
		ex.printStackTrace();
		return handleExceptionInternal(ex, createErrorMessage(ex.getStatus(), ex.getMessage(), null, ex.getParameters()), new HttpHeaders(), ex.getStatus(), request); 
	}

	private ErrorMessage createErrorMessage(final HttpStatus status, final String message, final List<FieldError> oErrors, final Object... args) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setStatus(status.value());
		errorMessage.setReasonPhrase(status.getReasonPhrase());
		errorMessage.setMessage(getMessage(message, args));

		if( oErrors != null && !oErrors.isEmpty() ) {
			errorMessage.setConstraints(createErrorMessageDetails(oErrors)); 
		}
		
		return errorMessage;
	}

	private List<ErrorMessageDetails> createErrorMessageDetails(final List<FieldError> oErrors) {
		List<ErrorMessageDetails> errors = new ArrayList<>();
		for (final FieldError oError : oErrors) {
			final String code = oError.getDefaultMessage();
		    final String messageField = getMessage(code, oError.getArguments());
		    errors.add(new ErrorMessageDetails(oError.getField(), messageField));
		}
		return errors;
	}
	
	private String getMessage(final String code, final Object... args) {
	    return messageSource.getMessage(code, args, Locale.getDefault());
	}
	
}

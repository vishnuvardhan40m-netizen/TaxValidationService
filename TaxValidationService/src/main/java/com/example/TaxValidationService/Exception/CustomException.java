package com.example.TaxValidationService.Exception;

import org.springframework.http.HttpStatus;

import com.example.TaxValidationService.Model.TaxException;

public class CustomException extends RuntimeException {
	
	private String message;
	public TaxException getException() {
		return exception;
	}
	public void setException(TaxException exception) {
		this.exception = exception;
	}
	
	private TaxException exception;
	private HttpStatus status;
	
	public CustomException(String message,  TaxException exception, HttpStatus status) {
		super(message);
		this.message = message;
		this.exception = exception;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	

}

package com.example.TaxValidationService.utils;

public class ExceptionCount {
	
	private int customerId;
	private long exceptionCount;
	public int getCustomerId() {
		return customerId;
	}
	public ExceptionCount() {
		super();
	}
	public ExceptionCount(int customerId, long exceptionCount) {
		super();
		this.customerId = customerId;
		this.exceptionCount = exceptionCount;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public long getExceptionCount() {
		return exceptionCount;
	}
	public void setExceptionCount(long exceptionCount) {
		this.exceptionCount = exceptionCount;
	}

}

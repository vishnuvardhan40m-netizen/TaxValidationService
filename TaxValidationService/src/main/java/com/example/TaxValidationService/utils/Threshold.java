package com.example.TaxValidationService.utils;

import java.math.BigDecimal;

public class Threshold {
	private BigDecimal threshold;

	public Threshold(BigDecimal threshold) {
		super();
		this.threshold = threshold;
	}

	public BigDecimal getThreshold() {
		return threshold;
	}

	public void setThreshold(BigDecimal threshold) {
		this.threshold = threshold;
	}
}

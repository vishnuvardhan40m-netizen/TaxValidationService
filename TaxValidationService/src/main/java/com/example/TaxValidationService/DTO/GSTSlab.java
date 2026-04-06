package com.example.TaxValidationService.DTO;

import java.math.BigDecimal;
public class GSTSlab {

    private BigDecimal threshold;
    private BigDecimal taxRate;

    public GSTSlab() {}   // ✅ required

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

	public BigDecimal getThreshold() {
		// TODO Auto-generated method stub
		return threshold;
	}
}
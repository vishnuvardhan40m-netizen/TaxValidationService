package com.example.TaxValidationService.utils;

import java.math.BigDecimal;

public class CustomerReport {

    private BigDecimal totalAmount;
    private BigDecimal totalReportedTax;
    private BigDecimal totalExpectedTax;
    private BigDecimal totalTaxGap;
    private int complianceScore;

    private long totalTransactions;
    private long validTransactions;

    public CustomerReport(
            BigDecimal totalAmount,
            BigDecimal totalReportedTax,
            BigDecimal totalExpectedTax,
            BigDecimal totalTaxGap,
            long totalTransactions,
            long validTransactions
    ) {
        this.totalAmount = totalAmount;
        this.totalReportedTax = totalReportedTax;
        this.totalExpectedTax = totalExpectedTax;
        this.totalTaxGap = totalTaxGap;
        this.totalTransactions = totalTransactions;
        this.validTransactions = validTransactions;

        this.complianceScore = calculateComplianceScore();
    }

    private int calculateComplianceScore() {
    	System.out.println(this.toString());
        if (totalTransactions == 0) return 0;

        double nonCompliancePercent =
                100-((double) validTransactions / (totalTransactions * 100));
System.out.println(nonCompliancePercent);
        return  (int) nonCompliancePercent;
    }

    // getters only (immutable DTO)

    @Override
	public String toString() {
		return "CustomerReport [totalAmount=" + totalAmount + ", totalReportedTax=" + totalReportedTax
				+ ", totalExpectedTax=" + totalExpectedTax + ", totalTaxGap=" + totalTaxGap + ", complianceScore="
				+ complianceScore + ", totalTransactions=" + totalTransactions + ", validTransactions="
				+ validTransactions + "]";
	}

	public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getTotalReportedTax() {
        return totalReportedTax;
    }

    public BigDecimal getTotalExpectedTax() {
        return totalExpectedTax;
    }

    public BigDecimal getTotalTaxGap() {
        return totalTaxGap;
    }

    public int getComplianceScore() {
        return complianceScore;
    }
}

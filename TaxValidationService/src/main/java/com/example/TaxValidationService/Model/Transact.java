package com.example.TaxValidationService.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import tools.jackson.databind.ObjectMapper;
@Entity
public class Transact {

	@Id
	private int transactionId;
	private LocalDate date;
	private int customerId;
	private BigDecimal amount;
	private BigDecimal taxrate;
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	@Enumerated(EnumType.STRING)
	private ValidationStatus validationStatus;
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public Transact(int transactionId, LocalDate date, int customerId, BigDecimal amount, BigDecimal taxrate,
			TransactionType transactionType, ValidationStatus validationStatus, BigDecimal reportedTax,
			String reasons) {
		super();
		this.transactionId = transactionId;
		this.date = date;
		this.customerId = customerId;
		this.amount = amount;
		this.taxrate = taxrate;
		this.transactionType = transactionType;
		this.validationStatus = validationStatus;
		this.reportedTax = reportedTax;
		this.reasons = reasons;
	}
	
	public Transact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TransactionType getTransactionType() {
	
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public ValidationStatus getValidationStatus() {
		return validationStatus;
	}
	public void setValidationStatus(ValidationStatus validationStatus) {
		this.validationStatus = validationStatus;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getTaxrate() {
		return taxrate;
	}
	public void setTaxrate(BigDecimal taxrate) {
		this.taxrate = taxrate;
	}
	public BigDecimal getReportedTax() {
		return reportedTax;
	}
	public void setReportedTax(BigDecimal reportedTax) {
		this.reportedTax = reportedTax;
	}
	public String getReasons() {
		return reasons;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	private BigDecimal reportedTax;
	
	private String reasons;
	
}

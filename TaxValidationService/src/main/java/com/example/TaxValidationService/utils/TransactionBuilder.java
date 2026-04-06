package com.example.TaxValidationService.utils;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.TaxValidationService.Model.Transact;
import com.example.TaxValidationService.Model.TransactionType;
import com.example.TaxValidationService.Model.ValidationStatus;


public class TransactionBuilder {
	
	private int transactionId;
	private LocalDate date;
	public TransactionBuilder(int transactionId, LocalDate date, int customerId, BigDecimal amount, BigDecimal taxrate,
			TransactionType transactionType, BigDecimal reportedTax) {
		super();
		this.transactionId = transactionId;
		this.date = date;
		this.customerId = customerId;
		this.amount = amount;
		this.taxrate = taxrate;
		this.transactionType = transactionType;
		this.reportedTax=reportedTax;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
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
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	private int customerId;
	private BigDecimal amount;
	private BigDecimal taxrate;
	private TransactionType transactionType;
	private BigDecimal reportedTax;
	
	public BigDecimal getReportedTax() {
		return reportedTax;
	}
	public void setReportedTax(BigDecimal reportedTax) {
		this.reportedTax = reportedTax;
	}
	public Transact buildTransaction(ValidationStatus s) {
		return new Transact(this.transactionId,this.date,this.customerId,this.amount,this.taxrate,this.transactionType,s,this.reportedTax,"abc");
	}}

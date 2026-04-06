package com.example.TaxValidationService.Model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
//@AllArgsConstructor
@NoArgsConstructor
public class TaxException {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int exceptionId;
	public TaxException( int transactionId, int customerId, String ruleName, String severity,
			String message, LocalDateTime date) {
		super();
		this.transactionId = transactionId;
		this.customerId = customerId;
		this.ruleName = ruleName;
		this.severity = severity;
		this.message = message;
		this.date = date;
	}
	private int transactionId;
	private int customerId;
	private String ruleName;
	private String severity;
	private String message;
	private LocalDateTime date;
	
	

}

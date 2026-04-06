package com.example.TaxValidationService.Model;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor

public class Log {

	private String eventType;
	public Log(String eventType, int transactionId, LocalDateTime localDate, String details) {
		super();
		this.eventType = eventType;
		this.transactionId = transactionId;
		this.date = localDate;
		this.details = details;
	}
	private int transactionId;
	private LocalDateTime date;
	private String details;
	@Override
	public String toString() {
		return "Log [eventType=" + eventType + ", transactionId=" + transactionId + ", date=" + date + ", details="
				+ details + "]";
	}
	
}

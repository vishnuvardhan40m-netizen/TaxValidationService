package com.example.TaxValidationService.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.websocket.Decoder.Text;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tools.jackson.databind.ObjectMapper;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RuleEngine {
	@Id
	private int ruleId;
	private String name;
	private String Description;
	@Enumerated(EnumType.STRING)
	private RuleType ruleType;
	private boolean enabled;
	@Column(columnDefinition="TEXT")
	private String config;
	
	
	
			
	


}

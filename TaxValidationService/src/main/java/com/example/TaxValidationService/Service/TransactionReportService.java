package com.example.TaxValidationService.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.TaxValidationService.Exception.CustomException;
import com.example.TaxValidationService.Model.ComplianceStatus;
import com.example.TaxValidationService.Model.Log;
import com.example.TaxValidationService.Model.RuleEngine;
import com.example.TaxValidationService.Model.TaxException;
import com.example.TaxValidationService.Model.Transact;
import com.example.TaxValidationService.Model.TransactionCompliance;
import com.example.TaxValidationService.Model.ValidationStatus;
import com.example.TaxValidationService.Repo.ExceptionRepo;
import com.example.TaxValidationService.Repo.RuleEngineRepo;
import com.example.TaxValidationService.Repo.TransactRepos;
import com.example.TaxValidationService.Repo.TransactionCompilanceRepo;
import com.example.TaxValidationService.Security.JwtService;
import com.example.TaxValidationService.Security.Userddeatils;
import com.example.TaxValidationService.utils.CustomerReport;
import com.example.TaxValidationService.utils.GSTSlab;
import com.example.TaxValidationService.utils.Threshold;
import com.example.TaxValidationService.utils.TransactionBuilder;

import tools.jackson.databind.ObjectMapper;

@Service
public class TransactionReportService {

	
		
	@Autowired
	public TransactRepos transactRepo;
	
	
	
	
	public List<CustomerReport> getReport() {
		
		return transactRepo.getReports();
	}
	
	
}

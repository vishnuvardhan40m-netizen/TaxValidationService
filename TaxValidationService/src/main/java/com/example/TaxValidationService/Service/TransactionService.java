package com.example.TaxValidationService.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.TaxValidationService.DTO.CustomerReport;
import com.example.TaxValidationService.DTO.GSTSlab;
import com.example.TaxValidationService.DTO.Threshold;
import com.example.TaxValidationService.DTO.TransactionBuilder;
import com.example.TaxValidationService.Exception.CustomException;
import com.example.TaxValidationService.Model.ComplianceStatus;
import com.example.TaxValidationService.Model.Log;
import com.example.TaxValidationService.Model.RuleEngine;
import com.example.TaxValidationService.Model.RuleType;
import com.example.TaxValidationService.Model.TaxException;
import com.example.TaxValidationService.Model.Transact;
import com.example.TaxValidationService.Model.RuleType;
import com.example.TaxValidationService.Model.TransactionCompliance;
import com.example.TaxValidationService.Model.ValidationStatus;
import com.example.TaxValidationService.Repo.ExceptionRepo;
import com.example.TaxValidationService.Repo.RuleEngineRepo;
import com.example.TaxValidationService.Repo.TransactRepos;
import com.example.TaxValidationService.Repo.TransactionCompilanceRepo;
import com.example.TaxValidationService.Security.JwtService;
import com.example.TaxValidationService.Security.Userddeatils;

import ch.qos.logback.classic.Logger;
import tools.jackson.databind.ObjectMapper;
@Service
public class TransactionService {
	
	
	@Autowired
	public  RuleEngineRepo ruleRepo;
	@Autowired
	public  AuthenticationManager authManager;
	@Autowired
	public TransactRepos transactRepo;
	@Autowired
	public  TransactionCompilanceRepo  compilanceRepo;
	@Autowired
	public ExceptionRepo exceptionRepo;
	@Autowired
	public JwtService jwtService;
	
	public static org.slf4j.Logger log=LoggerFactory.getLogger(TransactionService.class);
	
	public static boolean isValidTransaction(TransactionBuilder transaction) {
		
		log.info( new Log("Transactipn ingestion", transaction.getTransactionId(),LocalDateTime.now(),"transaction variable validation started").toString());
		
		if(!(transaction.getAmount().compareTo(BigDecimal.ZERO)>0) ){
			log.warn( new Log("Transactipn ingestion", transaction.getTransactionId(),LocalDateTime.now(),"Transaction amount is less than zero").toString());
			
			return false;
			
		}
		else if(transaction.getCustomerId()==0) {
			log.warn( new Log("Transactipn ingestion", transaction.getTransactionId(),LocalDateTime.now(),"invalid customer id").toString());
			
			return false;
		}
		else if(transaction.getDate()== null) {
			log.warn( new Log("Transactipn ingestion", transaction.getTransactionId(),LocalDateTime.now(),"invalid customer id").toString());
			
			
			return false;
		}
		
		else if (!(transaction.getTaxrate().compareTo(BigDecimal.ZERO)>0)) {
			log.warn( new Log("Transactipn ingestion", transaction.getTransactionId(),LocalDateTime.now(),"invalid tax rate").toString());
			
			return false;
			
		}else if (!(transaction.getTransactionId()!=0)) {
			log.warn( new Log("Transactipn ingestion", transaction.getTransactionId(),LocalDateTime.now(),"invalid transaction id").toString());
			
			return false;
		}else if(!(transaction.getTransactionType()!=null)) {
			log.warn( new Log("Transactipn ingestion", transaction.getTransactionId(),LocalDateTime.now(),"invalid transaction type").toString());
			
			return false;
		}
		log.info( new Log("Transactipn ingestion", transaction.getTransactionId(),LocalDateTime.now(),"transaction variable validation completed").toString());
		
		return true;
	}
	public  void calculateTaxGap(Transact transaction) {
		BigDecimal expectedTax=transaction.getAmount().multiply(transaction.getTaxrate());
		BigDecimal taxGap=expectedTax.subtract(transaction.getReportedTax());
		log.info( new Log("Tax Computation", transaction.getTransactionId(),LocalDateTime.now(),"Tax Gap validation started").toString());
		
		if(taxGap.abs().compareTo(BigDecimal.ONE)<=1) {
			TransactionCompliance tC=new TransactionCompliance(transaction, expectedTax,taxGap,ComplianceStatus.COMPLIANT);
			compilanceRepo.save(tC);
			log.info( new Log("Tax Computation", transaction.getTransactionId(),LocalDateTime.now(),"Tax Gap is Compliant").toString());
			
			
		}else if(taxGap.compareTo(BigDecimal.ONE)<-1) {
			
			TransactionCompliance tC=new TransactionCompliance(transaction, expectedTax,taxGap,ComplianceStatus.UNDERPAID);
			compilanceRepo.save(tC);

			log.info( new Log("Tax Computation", transaction.getTransactionId(),LocalDateTime.now(),"Tax is UnderPaid").toString());
			
		}else if(taxGap.compareTo(BigDecimal.ONE)>1) {
			
			TransactionCompliance tC=new TransactionCompliance(transaction, expectedTax,taxGap,ComplianceStatus.OVERPAID);
			compilanceRepo.save(tC);
			log.info( new Log("Tax Computation", transaction.getTransactionId(),LocalDateTime.now(),"Tax is OverPaid").toString());
			
		}

		log.info( new Log("Tax Computation", transaction.getTransactionId(),LocalDateTime.now(),"Tax Gap validation completed").toString());
		
	}
	public  void runRuleEngine(Transact transaction) {
		log.info( new Log("Rule Execution", transaction.getTransactionId(),LocalDateTime.now(),"Tax Rules Execution Started").toString());
		
		
		List<RuleEngine> Rules=ruleRepo.findAll();
	
		ObjectMapper mapper = new ObjectMapper();
		
		for(RuleEngine rule:Rules) {
			if(rule.isEnabled()) {
			switch (rule.getRuleType()){
			
			case HighValue:
				log.info( new Log("Rule Execution", transaction.getTransactionId(),LocalDateTime.now(),rule.getConfig()).toString());
				
				
				Threshold t= mapper.readValue(rule.getConfig(), Threshold.class);
				if (t.getThreshold().compareTo(transaction.getAmount())<0){
					log.info( new Log("Rule Execution", transaction.getTransactionId(),LocalDateTime.now(),rule.getConfig()).toString());
					TaxException e= new TaxException(transaction.getTransactionId(),transaction.getCustomerId(),rule.getRuleType().toString(),"High","Transaction Amout exceeded Threshold",LocalDateTime.now());
					throw new CustomException("Transaction amount exceeded threshold",e,HttpStatus.BAD_REQUEST);
				
					
				}
				break;
			case GSTSlab:
				log.info( new Log("Rule Execution", transaction.getTransactionId(),LocalDateTime.now(),rule.getConfig()).toString());
				
				GSTSlab g= mapper.readValue(rule.getConfig(), GSTSlab.class);
				System.out.println(rule.getConfig());
				if (g.getThreshold().compareTo(transaction.getAmount())<0){
					if(g.getTaxRate().compareTo(transaction.getTaxrate())>0) {
						log.info( new Log("Rule Execution", transaction.getTransactionId(),LocalDateTime.now(),rule.getConfig()).toString());
						TaxException e= new TaxException(transaction.getTransactionId(),transaction.getCustomerId(),rule.getRuleType().toString(),"GSTSlab","Transaction Amout exceeded Threshold with less InterestRate",LocalDateTime.now());
						throw new CustomException("Transaction amount exceeded threshold with less InterestRate",e,HttpStatus.BAD_REQUEST);

						
					}
				}
				
			}
			}
			
		}
		
	}

	public String validateTransaction(List<TransactionBuilder> transactions) {
		for(TransactionBuilder transaction:transactions) {
			if(TransactionService.isValidTransaction(transaction)) {
				
				
				Transact t=transaction.buildTransaction(ValidationStatus.SUCCESS);
				transactRepo.save(t);
				calculateTaxGap(t);
				runRuleEngine(t);
				
				
				
				
			}else {
				
				transactRepo.save(transaction.buildTransaction(ValidationStatus.FAILURE));
			}
			
			
		}
		return null;
	}

	public String generateToken(Object[] a) {
		// TODO Auto-generated method stub
		System.out.println("Credentials"+ a[0]+a[1]);
		Authentication auth= authManager.authenticate(new UsernamePasswordAuthenticationToken(a[0],String.valueOf(a[1])));
		if(auth.isAuthenticated()) {Userddeatils u=(Userddeatils) auth.getPrincipal();
		return jwtService.generateToken(u.getUser());}
		return null;
	}
	

}

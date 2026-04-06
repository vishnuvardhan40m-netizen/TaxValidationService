package com.example.TaxValidationService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaxValidationService.Service.TransactionReportService;

import com.example.TaxValidationService.utils.CustomerReport;

@RestController
public class TransactionReportController {
	
	 @Autowired 
	 private TransactionReportService transactionReportService;
	 
	
	 @GetMapping("/EntireReport")
	    public List<CustomerReport> getReport(){
		 
		 
			return transactionReportService.getReport();
	    	
	    }

}

package com.example.TaxValidationService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaxValidationService.DTO.CustomerReport;
import com.example.TaxValidationService.DTO.TransactionBuilder;
import com.example.TaxValidationService.Model.Transact;
import com.example.TaxValidationService.Service.TransactionService;

@RestController
public class TransactionController {
	
	
	 @Autowired 
	 private TransactionService transactionService;
	 
	 @PostMapping("/SendTransactions")
	 public String sendTransactions(@RequestBody List<TransactionBuilder> transactions) {
		 System.out.println("print");
    return transactionService.validateTransaction(transactions);
    
    
    
}
	
	

@GetMapping("/Login")
public String get1Report(@RequestBody Object[] a){
  System.out.println("Hello");
 
	return transactionService.generateToken(a);
	
}}



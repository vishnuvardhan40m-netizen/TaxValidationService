package com.example.TaxValidationService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaxValidationService.Service.ExceptionReportService;

@RestController
public class ExceptionController {
	
	@Autowired
	ExceptionReportService exceptionReportService;

	
	 @GetMapping("/TotalExceptions")
	    public long getExceptionReport(){
		 
		 
			return exceptionReportService.getTotalExceptions();
	    	
	    }
	    @GetMapping("/SeverityExceptions")
	    public long getExceptionReport(@RequestParam String severity){
		 
		 
			return exceptionReportService.getSeverityExceptions(severity);
	    	
	    }
	    @GetMapping("/CustomerExceptionsCount")
	    public long getExceptionReport(@RequestParam Integer customerId){
		 
		 
			return exceptionReportService.getCustomerExceptionCount(customerId);
	    	
	    }
}

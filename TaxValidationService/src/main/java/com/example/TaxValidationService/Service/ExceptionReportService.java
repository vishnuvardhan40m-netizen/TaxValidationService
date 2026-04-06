package com.example.TaxValidationService.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaxValidationService.Repo.ExceptionRepo;

@Service
public class ExceptionReportService {
@Autowired
ExceptionRepo exceptionRepo;

public List<Object[]> getExceptionReport() {
	
	return exceptionRepo.getExceptionSummary();
}

public long getSeverityExceptions(String sev) {
	
	return exceptionRepo.getCount(sev);
}

public long getTotalExceptions() {
	// TODO Auto-generated method stub
	return exceptionRepo.count();
	
	
}
public long getCustomerExceptionCount(Integer customerId) {
	return exceptionRepo.getCoCount(customerId);
}
}

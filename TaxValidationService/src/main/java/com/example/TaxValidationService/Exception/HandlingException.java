package com.example.TaxValidationService.Exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.TaxValidationService.Repo.ExceptionRepo;

@RestControllerAdvice
public class HandlingException {
	
	@Autowired
	ExceptionRepo repo;
	@ExceptionHandler(CustomException.class)
	public  void handlingException(CustomException exception ) {
		repo.save(exception.getException());
		
	}

}

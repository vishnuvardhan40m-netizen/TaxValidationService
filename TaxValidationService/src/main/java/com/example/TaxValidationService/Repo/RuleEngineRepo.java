package com.example.TaxValidationService.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TaxValidationService.Model.RuleEngine;

@Repository
public interface RuleEngineRepo extends JpaRepository<RuleEngine,Integer> {
	
	

}

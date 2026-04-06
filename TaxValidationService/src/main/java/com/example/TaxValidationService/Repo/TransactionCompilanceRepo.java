package com.example.TaxValidationService.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TaxValidationService.Model.TransactionCompliance;
@Repository
public interface TransactionCompilanceRepo extends JpaRepository<TransactionCompliance, Integer> {

}

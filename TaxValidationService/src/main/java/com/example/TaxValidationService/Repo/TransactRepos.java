package com.example.TaxValidationService.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.TaxValidationService.Model.Transact;
import com.example.TaxValidationService.utils.CustomerReport;


@Repository
public interface TransactRepos extends JpaRepository<Transact , Integer> {



   
    		@Query(


"SELECT new com.example.TaxValidationService.utils.CustomerReport(" +
    "   SUM(t.amount), " +
    "   SUM(tc.expectedTax), " +
    "   SUM(tc.expectedTax), " +
    "   SUM(tc.taxGap), " +
    "   COUNT(tc), " +
    "   SUM(CASE WHEN tc.complianceStatus = 'COMPLIANT' THEN 1 ELSE 0 END)" +
    ") " +
    "FROM TransactionCompliance tc " +
    "JOIN tc.transaction t " +
    "WHERE t.validationStatus = 'SUCCESS'"


    			
    )
	List<CustomerReport > getReports();

}

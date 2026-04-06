package com.example.TaxValidationService.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.TaxValidationService.DTO.CustomerReport;
import com.example.TaxValidationService.Model.Transact;


@Repository
public interface TransactRepos extends JpaRepository<Transact , Integer> {



   


@Query(
    "SELECT new com.example.TaxValidationService.DTO.CustomerReport(" +
    "   t.customerId, " +
    "   SUM(t.amount), " +
    "   SUM(COALESCE(t.reportedTax, 0)), " +
    "   SUM(tc.expectedTax), " +
    "   SUM(tc.taxGap), " +
    "   COUNT(tc), " +
    "   SUM(CASE WHEN tc.complianceStatus = 'COMPLIANT' THEN 1 ELSE 0 END) " +
    ") " +
    "FROM TransactionCompliance tc " +
    "JOIN tc.transaction t " +
    "WHERE t.validationStatus = 'SUCCESS' " +
    "GROUP BY t.customerId"
)
	List<CustomerReport > getReports();

}

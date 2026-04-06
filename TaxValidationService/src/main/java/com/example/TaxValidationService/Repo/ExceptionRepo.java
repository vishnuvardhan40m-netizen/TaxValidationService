package com.example.TaxValidationService.Repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;  // ✅ CORRECT
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.TaxValidationService.Model.TaxException;

@Repository
public interface ExceptionRepo extends JpaRepository<TaxException, Integer> {

    @Query(
        value =
            "SELECT 'TOTAL' AS type, NULL AS customer_id, NULL AS severity, COUNT(*) AS exception_count " +
            "FROM dbo.tax_exception " +

            "UNION ALL " +

            "SELECT 'SEVERITY' AS type, NULL AS customer_id, severity AS severity, COUNT(*) AS exception_count " +
            "FROM dbo.tax_exception " +
            "GROUP BY severity " +

            "UNION ALL " +

            "SELECT 'CUSTOMER' AS type, customer_id AS customer_id, NULL AS severity, COUNT(*) AS exception_count " +
            "FROM dbo.tax_exception " +
            "GROUP BY customer_id",
        nativeQuery = true
    )
    List<Object[]> getExceptionSummary();
    @Query("SELECT COUNT(e) FROM TaxException e WHERE e.severity = :sev")
	long getCount(@Param("sev") String sev);
    
    @Query("SELECT COUNT(e) FROM TaxException e WHERE e.customerId = :id")
	long getCoCount(@Param("id") Integer id);
    

    
}
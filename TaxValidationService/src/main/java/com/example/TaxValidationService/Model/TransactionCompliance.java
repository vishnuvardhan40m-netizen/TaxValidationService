package com.example.TaxValidationService.Model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction_compliance")
public class TransactionCompliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(
        name = "transaction_id",
        nullable = false,
        unique = true
    )
    private Transact transaction;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal expectedTax;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal taxGap;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ComplianceStatus complianceStatus;

    // ✅ JPA requires this
    protected TransactionCompliance() {
    }

    public TransactionCompliance(
            Transact transaction,
            BigDecimal expectedTax,
            BigDecimal taxGap,
            ComplianceStatus complianceStatus) {

        this.transaction = transaction;
        this.expectedTax = expectedTax;
        this.taxGap = taxGap;
        this.complianceStatus = complianceStatus;
    }

    // getters & setters

    public Long getId() {
        return id;
    }

    public Transact getTransaction() {
        return transaction;
    }

    public void setTransaction(Transact transaction) {
        this.transaction = transaction;
    }

    public BigDecimal getExpectedTax() {
        return expectedTax;
    }

    public void setExpectedTax(BigDecimal expectedTax) {
        this.expectedTax = expectedTax;
    }

    public BigDecimal getTaxGap() {
        return taxGap;
    }

    public void setTaxGap(BigDecimal taxGap) {
        this.taxGap = taxGap;
    }

    public ComplianceStatus getComplianceStatus() {
        return complianceStatus;
    }

    public void setComplianceStatus(ComplianceStatus complianceStatus) {
        this.complianceStatus = complianceStatus;
    }
}
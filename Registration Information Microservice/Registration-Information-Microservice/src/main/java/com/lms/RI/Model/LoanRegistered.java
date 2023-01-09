package com.lms.RI.Model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loan_registered")
public class LoanRegistered {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "borrower_id")
    private int borrowerId;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "asked_loan_amount")
    private String askedLoanAmt;

    @Column(name = "provided_loan_amount")
    private String providedLoanAmt;

    @Column(name = "payment_period")
    private int paymentPeriod;

    @Column(name = "emi_amount")
    private String emiAmt;

    @Column(name = "issued_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date issuedDate;

    @OneToOne(cascade = CascadeType.ALL)
    private GuarantorInfo guarantorInfo;

    public LoanRegistered(int borrowerId, String loanType, String askedLoanAmt, String providedLoanAmount, 
            int paymentPeriod, String emiAmt, Date issuedDate, GuarantorInfo guarantorInfo) {
        this.borrowerId = borrowerId;
        this.loanType = loanType;
        this.askedLoanAmt = askedLoanAmt;
        this.providedLoanAmt = providedLoanAmount;
        this.paymentPeriod = paymentPeriod;
        this.emiAmt = emiAmt;
        this.issuedDate = issuedDate;
        this.guarantorInfo = guarantorInfo;
    }
}


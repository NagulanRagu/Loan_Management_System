package com.lms.LI.Model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LoanRegistered {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Registration Id")
    private int id;

    @Column(name = "Borrower Id")
    private int borrowerId;

    @Column(name = "Loan Number")
    private String loanNo;

    @Column(name = "Loan Amount")
    private String loanAmt;

    @Column(name = "Payment Period")
    private int paymentPeriod;

    @Column(name = "EMI Amount")
    private String emiAmt;

    @Column(name = "Issued Date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date issuedDate;

    @Column(name = "Property Details")
    private String propertyDetails;

    public LoanRegistered(int borrowerId, String loanNo, String loanAmt, int paymentPeriod, String emiAmt,
            Date issuedDate, String propertyDetails) {
        this.borrowerId = borrowerId;
        this.loanNo = loanNo;
        this.loanAmt = loanAmt;
        this.paymentPeriod = paymentPeriod;
        this.emiAmt = emiAmt;
        this.issuedDate = issuedDate;
        this.propertyDetails = propertyDetails;
    }
}

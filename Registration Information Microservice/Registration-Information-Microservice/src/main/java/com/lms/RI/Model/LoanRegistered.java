package com.lms.RI.Model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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

    @Column(name = "Loan Type")
    private String loanType;

    @Column(name = "Loan Amount")
    private String loanAmt;

    @Column(name = "Payment Period")
    private int paymentPeriod;

    @Column(name = "EMI Amount")
    private String emiAmt;

    @Column(name = "Issued Date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date issuedDate;

    @OneToOne(fetch = FetchType.LAZY,
              cascade = CascadeType.ALL,
              mappedBy = "registered")
    private GuarantorInfo guarantorInfo;

    public LoanRegistered(int borrowerId, String loanType, String loanAmt, int paymentPeriod, String emiAmt,
            Date issuedDate, GuarantorInfo guarantorInfo) {
        this.borrowerId = borrowerId;
        this.loanType = loanType;
        this.loanAmt = loanAmt;
        this.paymentPeriod = paymentPeriod;
        this.emiAmt = emiAmt;
        this.issuedDate = issuedDate;
        this.guarantorInfo = guarantorInfo;
    }
}


package com.lms.LI.Model;

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
public class LoanDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Loan Number")
    private String loanNo;

    @Column(name = "Loan Type")
    private String loanType;

    @Column(name = "Rate Of Interest")
    private int rateOfInterest;

    public LoanDetails(String loanNo, String loanType, int rateOfInterest) {
        this.loanNo = loanNo;
        this.loanType = loanType;
        this.rateOfInterest = rateOfInterest;
    }
}

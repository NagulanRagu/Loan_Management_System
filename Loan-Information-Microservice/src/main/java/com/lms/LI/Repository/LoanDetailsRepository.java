package com.lms.LI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.LI.Model.LoanDetails;

public interface LoanDetailsRepository extends JpaRepository<LoanDetails, Integer> {
    
    LoanDetails findById(int id);
    
    LoanDetails findByLoanType(String loanType);

    LoanDetails findByLoanNo(String loanNo);
}

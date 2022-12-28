package com.lms.LI.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.LI.Model.LoanDetails;

@Repository
public interface LoanDetailsRepository extends JpaRepository<LoanDetails, Integer> {
    
    LoanDetails findById(int id);
    
    List<LoanDetails> findByLoanType(String loanType);

    LoanDetails findByLoanNo(String loanNo);
}

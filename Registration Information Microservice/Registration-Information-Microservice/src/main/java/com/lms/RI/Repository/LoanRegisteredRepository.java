package com.lms.RI.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.RI.Model.LoanRegistered;

public interface LoanRegisteredRepository extends JpaRepository<LoanRegistered, Integer> {
    
    List<LoanRegistered> findByLoanNo(String loanNo);

    List<LoanRegistered> findByBorrowerId(int id);
}

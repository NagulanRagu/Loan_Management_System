package com.lms.LI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.LI.Model.LoanRegistered;

@Repository
public interface LoanRegisteredRepository extends JpaRepository<LoanRegistered, Integer> {
    
}

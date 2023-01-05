package com.lms.RI.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.RI.Model.LoanRegistered;
import com.lms.RI.Repository.LoanRegisteredRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoanRegisteredService {
    
    @Autowired
    LoanRegisteredRepository loanRegisteredRepository;

    public List<LoanRegistered> getAllDetails() throws NullPointerException {

        log.info("Getting all Details from Database.");
        if(loanRegisteredRepository.findAll().isEmpty()) {
            log.error("No Details is found in Database.");
            throw new NullPointerException();
        }else {
            log.info("All Details are fetched.");
            return loanRegisteredRepository.findAll();
        }
    }

    public LoanRegistered saveDetails(LoanRegistered loanRegistered) {

        log.info("Saving the Details {} to the Database.", loanRegistered);
        return loanRegisteredRepository.save(loanRegistered);
    }

    public void deleteDetails(int id) {

        log.info("Deleting the Details from Database for Id: {}", id);
        loanRegisteredRepository.deleteById(id);
    }

    public List<LoanRegistered> getByLoanType(String loanType) throws IllegalArgumentException {

        log.info("Getting Details by Loan Number: {}", loanType);
        if(loanRegisteredRepository.findByLoanType(loanType).isEmpty()) {
            log.error("No Details found for Loan Number: {}", loanType);
            return loanRegisteredRepository.findByLoanType(loanType);
        }else {
            log.info("All Details are found for Loan Number: {}", loanType);
            throw new IllegalArgumentException();
        }
    }

    public List<LoanRegistered> getByBorrowerId(int id) throws IllegalArgumentException {

        log.info("Getting Details by Borrower Id: {}", id);
        if(loanRegisteredRepository.findByBorrowerId(id).isEmpty()) {
            log.error("No Details found for Borrower Id: {}", id);
            return loanRegisteredRepository.findByBorrowerId(id);
        }else {
            log.info("All Details are found for Borrower Id: {}", id);
            throw new IllegalArgumentException();
        }
    }
}

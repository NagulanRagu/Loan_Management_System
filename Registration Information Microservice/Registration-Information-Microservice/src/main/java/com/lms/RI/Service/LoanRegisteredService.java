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
        if (loanRegisteredRepository.findAll().isEmpty()) {
            log.error("No Details is found in Database.");
            throw new NullPointerException("No Details Present in Database");
        } else {
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

    public LoanRegistered getById(int id) throws IllegalArgumentException {

        log.info("Getting Details by Loan Id: {}", id);
        if (loanRegisteredRepository.findById(id) != null) {
            log.info("All Details are found for Loan Id: {}", id);
            return loanRegisteredRepository.findById(id);
        } else {
            log.error("No Detail found for Loan Id: {}", id);
            throw new IllegalArgumentException("No Detail found for the Loan Id: " + id);
        }
    }

    public List<LoanRegistered> getByBorrowerName(String borrowerName) throws IllegalArgumentException {

        log.info("Getting Details by Borrower Name: {}", borrowerName);
        if (loanRegisteredRepository.findByBorrowerName(borrowerName).isEmpty()) {
            log.error("No Details found for Borrower Name: {}", borrowerName);
            throw new IllegalArgumentException("No Details found for Borrower Name: " + borrowerName);
        } else {
            log.info("All Details are found for Borrower borrowerName: {}", borrowerName);
            return loanRegisteredRepository.findByBorrowerName(borrowerName);
        }
    }
}

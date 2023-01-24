package com.lms.LI.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.LI.Model.LoanDetails;
import com.lms.LI.Repository.LoanDetailsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoanDetailsService {
    
    @Autowired
    LoanDetailsRepository loanDetailsRepository;

    public List<LoanDetails> getAllDetails() throws NullPointerException {

        log.info("Getting all Details from Database.");
        if(loanDetailsRepository.findAll().isEmpty()) {
            log.error("No Details is found in Database.");
            throw new NullPointerException("No Details Present");
        }else {
            log.info("All Details are fetched.");
            return loanDetailsRepository.findAll();
        }
    }

    public LoanDetails getById(int id) {

        log.info("Getting Details from Database for Loan Id: {}.", id);
        if(loanDetailsRepository.findById(id) == null) {
            log.error("No Details is found in Database for Loan id: {}.", id);
            throw new IllegalArgumentException("No Details is found for Loan id.");
        }else {
            log.info("Details are fetched for Loan id: {}.", id);
            return loanDetailsRepository.findById(id);
        }
    }

    public List<LoanDetails> getByType(String loanType) throws IllegalArgumentException {

        log.info("Getting all Details from Database for Loan Type: {}.",loanType);
        if(loanDetailsRepository.findByLoanType(loanType) == null) {
            log.error("No Details is found in Database for Loan Type: {}.", loanType);
            throw new IllegalArgumentException("No Details is found for Loan Type");
        }else {
            log.info("All Details are fetched for Loan Type: {}.", loanType);
            return loanDetailsRepository.findByLoanType(loanType);
        }
    }

    public LoanDetails getByNumber(String loanNo) throws IllegalArgumentException {

        log.info("Getting all Details from Database for Loan Number: {}.", loanNo);
        if(loanDetailsRepository.findByLoanNo(loanNo) == null) {
            log.error("No Details is found in Database for Loan Number: {}.", loanNo);
            throw new IllegalArgumentException("No Details is found for Loan Number");
        }else {
            log.info("All Details are fetched for Loan Number: {}.", loanNo);
            return loanDetailsRepository.findByLoanNo(loanNo);
        }
    }

    public LoanDetails saveDetails(LoanDetails loanDetails) {

        log.info("Saving the Details {} to the Database.", loanDetails);
        return loanDetailsRepository.save(loanDetails);
    }

    public LoanDetails updateDetails(LoanDetails updateDetails) {

        log.info("Updating the Details: {}", updateDetails);
        getById(updateDetails.getId());
        deleteDetails(updateDetails.getId());
        return saveDetails(updateDetails);
    }

    public void deleteDetails(int id) {

        log.info("Deleting the Details from Database for Id: {}", id);
        loanDetailsRepository.deleteById(id);
    }
}

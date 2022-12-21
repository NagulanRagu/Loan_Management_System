package com.lms.LI.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.LI.Model.LoanRegistered;
import com.lms.LI.Repository.LoanRegisteredRepository;

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
}

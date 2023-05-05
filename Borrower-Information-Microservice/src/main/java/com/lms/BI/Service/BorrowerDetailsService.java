
package com.lms.BI.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.BI.FeignClient.FeignEmailSender;
import com.lms.BI.Model.BorrowerDetails;
import com.lms.BI.Model.ConfirmationToken;
import com.lms.BI.Model.Role;
import com.lms.BI.Pojo.EmailSender;
import com.lms.BI.Repository.BorrowerDetailsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BorrowerDetailsService {

    @Autowired
    BorrowerDetailsRepository borrowerDetailsRepository;
    
    @Autowired
    ConfirmationTokenService confirmationTokenService;
    
    @Autowired
    FeignEmailSender feignEmailSender;

    public List<BorrowerDetails> getAllDetails() throws NullPointerException {

        log.info("Getting all Borrower Details From Database.");
        List<BorrowerDetails> allDetails = borrowerDetailsRepository.findAll();
        if (allDetails.isEmpty()) {
            log.error("No Borrower Details found from Database.");
            throw new NullPointerException("No Details Present");
        } else {
            log.info("All Details are fetched from Database.");
            return allDetails;
        }
    }

    public BorrowerDetails getById(int id) throws IllegalArgumentException {

        log.info("Getting Details from Database for Id: {}", id);
        BorrowerDetails getDetail = borrowerDetailsRepository.findById(id);
        if (getDetail == null) {
            log.error("No Borrower Detail found for Id: {}", id);
            throw new IllegalArgumentException("No Details is found for this Id");
        } else {
            log.info("Details are fetched for Id: {}", id);
            return getDetail;
        }
    }

    public BorrowerDetails getByUname(String uname) throws IllegalArgumentException {

        log.info("Getting Details from Database for User Name: {}", uname);
        BorrowerDetails getDetail = borrowerDetailsRepository.findByUname(uname);
        if (getDetail == null) {
            log.error("No Borrower Detail found for User Name: {}", uname);
            throw new IllegalArgumentException("No Details is found for this User Name");
        } else {
            log.info("Details are fetched for User Name: {}", uname);
            return getDetail;
        }
    }

    public boolean checkUname(String uname) {
        return borrowerDetailsRepository.existsByUname(uname);
    }

    public boolean checkEmailId(String emailId) {
        return borrowerDetailsRepository.existsByEmailId(emailId);
    }

    public BorrowerDetails saveBorrowerDetail(BorrowerDetails nBorrowerDetails) {

        log.info("Adding Detail to the Database: {}", nBorrowerDetails);
        if (nBorrowerDetails.getRoles() == null) {
            log.info("Setting the role to ROLE_USER");
            Set<Role> roles = new HashSet<>();
            roles.add(new Role("ROLE_USER"));
            nBorrowerDetails.setRoles(roles);
        }
        BorrowerDetails savedBorrowerDetails = borrowerDetailsRepository.save(nBorrowerDetails);
        ConfirmationToken confirmationToken = confirmationTokenService.createConfirmationToken(savedBorrowerDetails);
        log.info("Confirmation Token is Generated: {}", confirmationToken);
//        Map<String, String> variables = new HashMap<>();
//        variables.put("link", "http://localhost:8080/token/confirm/" + confirmationToken.getToken());
//        EmailSender emailSender = new EmailSender(savedBorrowerDetails.getEmailId(), "confirmationToken", variables);
//        log.info("Email Sender is created: {} and send mail is send", emailSender);
//        feignEmailSender.sendMail(emailSender);
        return savedBorrowerDetails;
    }
    
    public String updateEnable(int id) {
    	log.info("Updating the Enable to true for User id: {}", id);
    	BorrowerDetails borrowerDetails = getById(id);
    	borrowerDetails.setEnabled(true);
    	borrowerDetailsRepository.save(borrowerDetails);
    	return "Enabled";
    }

    public BorrowerDetails updateBorrowerDetail(BorrowerDetails uBorrowerDetails) {

        log.info("Updating Detail to the Database: {}", uBorrowerDetails);
        BorrowerDetails oBorrowerDetails = borrowerDetailsRepository.findById(uBorrowerDetails.getId());
        uBorrowerDetails.setRoles(oBorrowerDetails.getRoles());
        return borrowerDetailsRepository.save(uBorrowerDetails);
    }

    public void deleteBorrowerDetail(int id) {

        log.info("Deleting the Detail from Database for Id: {}", id);
        borrowerDetailsRepository.deleteById(id);
    }
}

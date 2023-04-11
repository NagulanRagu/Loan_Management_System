package com.lms.LI.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LI.Model.LoanDetails;
import com.lms.LI.Service.LoanDetailsService;
import com.lms.LI.Service.TokenService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoanDetailsController {

    @Autowired
    LoanDetailsService loanDetailsService;

    @Autowired
    TokenService tokenService;

    @GetMapping("/all-loan")
    public ResponseEntity<?> getAllDetails(@RequestHeader(name = "Authorization") String token) {

        if (tokenService.checkValidation(token)) {
            try {
                return new ResponseEntity<>(loanDetailsService.getAllDetails(), HttpStatus.OK);
            } catch (NullPointerException e) {
                return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/loan-by-id/{id}")
    public ResponseEntity<?> getById(@RequestHeader("Authorization") String token, @PathVariable int id) {

        if (tokenService.checkValidation(token)) {
            try {
                return new ResponseEntity<>(loanDetailsService.getById(id), HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/loan-by-loanType/{loanType}")
    public ResponseEntity<?> getByType(@RequestHeader("Authorization") String token,
            @PathVariable String loanType) {

        if (tokenService.checkValidation(token)) {
            try {
                return new ResponseEntity<>(loanDetailsService.getByType(loanType), HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/add-loan")
    public ResponseEntity<LoanDetails> saveEntity(@RequestHeader("Authorization") String token,
            @RequestBody LoanDetails newLoanDetails) {

        if (tokenService.checkValidation(token)) {
            return new ResponseEntity<>(loanDetailsService.saveDetails(newLoanDetails), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/update-loan/{id}")
    public ResponseEntity<?> updateEntity(@RequestHeader("Authorization") String token,
            @RequestBody LoanDetails updateDetails) {

        if (tokenService.checkValidation(token)) {
                return new ResponseEntity<>(loanDetailsService.updateDetails(updateDetails), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/delete-loan/{id}")
    public ResponseEntity<String> deleteEntity(@RequestHeader("Authorization") String token, @PathVariable int id) {

        if (tokenService.checkValidation(token)) {
            loanDetailsService.deleteDetails(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}

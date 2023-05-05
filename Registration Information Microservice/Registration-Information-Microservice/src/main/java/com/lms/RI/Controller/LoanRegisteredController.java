package com.lms.RI.Controller;

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

import com.lms.RI.Model.LoanRegistered;
import com.lms.RI.Service.LoanRegisteredService;
import com.lms.RI.Service.TokenService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoanRegisteredController {

    @Autowired
    LoanRegisteredService loanRegisteredService;

    @Autowired
    TokenService tokenService;

    @GetMapping("/all-registration")
    public ResponseEntity<?> getAllDetails(@RequestHeader("Authorization") String token) {

        if (tokenService.checkValidation(token)) {
            try {
                return new ResponseEntity<>(loanRegisteredService.getAllDetails(), HttpStatus.OK);
            } catch (NullPointerException e) {
                return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/registration-by-id/{id}")
    public ResponseEntity<?> getByLoanId(@RequestHeader("Authorization") String token,
            @PathVariable int id) {

        if (tokenService.checkValidation(token)) {
            try {
                return new ResponseEntity<>(loanRegisteredService.getById(id), HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/registration-by-BorrowerName/{borrowerName}")
    public ResponseEntity<?> getByBorrowerId(@RequestHeader("Authorization") String token,
            @PathVariable String borrowerName) {

        if (tokenService.checkValidation(token)) {
            try {
                return new ResponseEntity<>(loanRegisteredService.getByBorrowerName(borrowerName), HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/apply")
    public ResponseEntity<?> saveEntity(@RequestHeader("Authorization") String token,
            @RequestBody LoanRegistered newLoanRegistered) {

        if (tokenService.checkValidation(token)) {
            try {
                return new ResponseEntity<>(loanRegisteredService.saveDetails(newLoanRegistered), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/update-registration")
    public ResponseEntity<?> updateEntity(@RequestHeader("Authorization") String token,
            @RequestBody LoanRegistered updateLoanRegistered) {
        if (tokenService.checkValidation(token)) {
            try {
                return new ResponseEntity<>(loanRegisteredService.saveDetails(updateLoanRegistered),
                        HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/delete-registration/{id}")
    public ResponseEntity<String> deleteEntity(@RequestHeader("Authorization") String token,
            @PathVariable int id) {

        if (tokenService.checkValidation(token)) {
            loanRegisteredService.deleteDetails(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}

package com.lms.LI.Controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.lms.LI.Model.LoanDetails;
import com.lms.LI.Service.LoanDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoanDetailsController {
    
    @Autowired
    LoanDetailsService loanDetailsService;

    @GetMapping("/all-loan")
    public ResponseEntity<List<LoanDetails>> getAllDetails() {

        try {
            return new ResponseEntity<>(loanDetailsService.getAllDetails(), HttpStatus.OK);
        }catch(NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/loan-by-id/{id}")
    public ResponseEntity<LoanDetails> getById(@PathVariable int id) {

        try {
            return new ResponseEntity<>(loanDetailsService.getById(id), HttpStatus.OK);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/loan-by-loanType/{loanType}")
    public ResponseEntity<List<LoanDetails>> getByType(@PathVariable String loanType) {

        try {
            return new ResponseEntity<>(loanDetailsService.getByType(loanType), HttpStatus.OK);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/loan-by-loanNo/{loanNo}")
    public ResponseEntity<LoanDetails> getByNumber(@PathVariable String loanNo) {

        try {
            return new ResponseEntity<>(loanDetailsService.getByNumber(loanNo), HttpStatus.OK);
        }catch(NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add-loan")
    public ResponseEntity<LoanDetails> saveEntity(@RequestBody LoanDetails newLoanDetails) {

        try {
            return new ResponseEntity<>(loanDetailsService.saveDetails(newLoanDetails), HttpStatus.CREATED);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-loan/{id}")
    public ResponseEntity<LoanDetails> updateEntity(@RequestBody LoanDetails updateDetails) {

        try {
            return new ResponseEntity<>(loanDetailsService.updateDetails(updateDetails), HttpStatus.OK);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-loan/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable int id) {

        loanDetailsService.deleteDetails(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

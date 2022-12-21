package com.lms.LI.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LI.Model.LoanDetails;
import com.lms.LI.Service.LoanDetailsService;

@RestController
@RequestMapping("/loan")
public class LoanDetailsController {
    
    @Autowired
    LoanDetailsService loanDetailsService;

    @GetMapping("/")
    public ResponseEntity<List<LoanDetails>> getAllDetails() {

        try {
            return new ResponseEntity<>(loanDetailsService.getAllDetails(), HttpStatus.FOUND);
        }catch(NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{loanType}")
    public ResponseEntity<List<LoanDetails>> getByType(@PathVariable String loanType) {

        try {
            return new ResponseEntity<>(loanDetailsService.getByType(loanType), HttpStatus.FOUND);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{loanNo}")
    public ResponseEntity<LoanDetails> getByNumber(@PathVariable String loanNo) {

        try {
            return new ResponseEntity<>(loanDetailsService.getByNumber(loanNo), HttpStatus.FOUND);
        }catch(NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<LoanDetails> saveEntity(@RequestBody LoanDetails newLoanDetails) {

        try {
            return new ResponseEntity<>(loanDetailsService.saveDetails(newLoanDetails), HttpStatus.CREATED);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable int id) {

        loanDetailsService.deleteDetails(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

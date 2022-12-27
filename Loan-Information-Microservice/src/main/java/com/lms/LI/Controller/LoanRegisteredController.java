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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LI.Model.LoanRegistered;
import com.lms.LI.Service.LoanRegisteredService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoanRegisteredController {
    
    @Autowired
    LoanRegisteredService loanRegisteredService;

    @GetMapping("/all-registration")
    public ResponseEntity<List<LoanRegistered>> getAllDetails() {

        try {
            return new ResponseEntity<>(loanRegisteredService.getAllDetails(), HttpStatus.OK);
        }catch(NullPointerException e) {
            return new ResponseEntity<>(loanRegisteredService.getAllDetails(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/registration-by-loanNo/{loanNo}")
    public ResponseEntity<List<LoanRegistered>> getByLoanNo(@PathVariable String loanNo) {

        try {
            return new ResponseEntity<>(loanRegisteredService.getByLoanNo(loanNo), HttpStatus.OK);
        }catch(NullPointerException e) {
            return new ResponseEntity<>(loanRegisteredService.getByLoanNo(loanNo),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/registration-by-BorrowerId/{borrowerId}")
    public ResponseEntity<List<LoanRegistered>> getByBorrowerId(@PathVariable int borrowerId) {

        try {
            return new ResponseEntity<>(loanRegisteredService.getByBorrowerId(borrowerId), HttpStatus.OK);
        }catch(NullPointerException e) {
            return new ResponseEntity<>(loanRegisteredService.getByBorrowerId(borrowerId),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/apply")
    public ResponseEntity<LoanRegistered> saveEntity(@RequestBody LoanRegistered newLoanRegistered) {

        try {
            return new ResponseEntity<>(loanRegisteredService.saveDetails(newLoanRegistered),HttpStatus.OK);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-registration/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable int id) {

        loanRegisteredService.deleteDetails(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

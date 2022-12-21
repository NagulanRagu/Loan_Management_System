package com.lms.BI.Controller;

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

import com.lms.BI.Model.BorrowerDetails;
import com.lms.BI.Pojo.LoginCredentails;
import com.lms.BI.Service.BorrowerDetailsService;

@RestController
@RequestMapping("/user")
public class BorrowerDetailsController {
    
    @Autowired
    BorrowerDetailsService borrowerDetailsService;

    @GetMapping("/{id}")
    public ResponseEntity<BorrowerDetails> getById(@PathVariable int id) {

        try {
            return new ResponseEntity<>(borrowerDetailsService.getById(id), HttpStatus.FOUND);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<BorrowerDetails>> getAllUsers() {

        try {
            return new ResponseEntity<>(borrowerDetailsService.getAllDetails(), HttpStatus.FOUND);
        }catch(NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<BorrowerDetails> saveEntity(@RequestBody BorrowerDetails nBorrowerDetails) {

        try {
            return new ResponseEntity<>(borrowerDetailsService.saveBorrowerDetail(nBorrowerDetails), HttpStatus.CREATED);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginCredentails loginCredentails) {

        BorrowerDetails borrowerDetails = borrowerDetailsService.getByUname(loginCredentails.getUname());
        try {
            if(borrowerDetails.getPassword().equals(loginCredentails.getPassword())) {
                return new ResponseEntity<>("Login is Successfull.", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Password is Wrong.", HttpStatus.FORBIDDEN);
            }
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<>("Username is Wrong.", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletEntity(@PathVariable int id) {

        try {
            borrowerDetailsService.deleteBorrowerDetail(id);
            return new ResponseEntity<>("Details is Deleted.", HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>("Error occured in Deleting.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.lms.BI.Controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.BI.Model.BorrowerDetails;
import com.lms.BI.Pojo.HttpCall;
import com.lms.BI.Pojo.LoginCredentails;
import com.lms.BI.Service.BorrowerDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping("/signup")
    public ResponseEntity<BorrowerDetails> saveEntity(@RequestBody BorrowerDetails nBorrowerDetails) {

        try {
            return new ResponseEntity<>(borrowerDetailsService.saveBorrowerDetail(nBorrowerDetails), HttpStatus.CREATED);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<HttpCall> login(@RequestBody LoginCredentails loginCredentails) {

        HttpCall httpCall = new HttpCall();
        httpCall.setRequest("Login call is Requested.");
        BorrowerDetails borrowerDetails = borrowerDetailsService.getByUname(loginCredentails.getUname());
        try {
            if(borrowerDetails.getPassword().equals(loginCredentails.getPassword())) {
                httpCall.setReponse("Login is Successfull.");
                return new ResponseEntity<>(httpCall, HttpStatus.OK);
            }else {
                httpCall.setReponse("Password is Wrong.");
                return new ResponseEntity<>(httpCall, HttpStatus.FORBIDDEN);
            }
        }catch(IllegalArgumentException e) {
            httpCall.setReponse("Username is Wrong.");
            return new ResponseEntity<>(httpCall, HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpCall> deletEntity(@PathVariable int id) {

        HttpCall httpCall = new HttpCall();
        httpCall.setRequest("Delete call is Requested.");
        try {
            borrowerDetailsService.deleteBorrowerDetail(id);
            httpCall.setReponse("Details is Deleted.");
            return new ResponseEntity<>(httpCall, HttpStatus.OK);
        }catch(Exception e) {
            httpCall.setReponse("Error occured in Deleting.");
            return new ResponseEntity<>(httpCall, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

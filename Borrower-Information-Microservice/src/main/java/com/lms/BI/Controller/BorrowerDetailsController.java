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
import org.springframework.web.bind.annotation.RestController;

import com.lms.BI.Model.BorrowerDetails;
import com.lms.BI.Pojo.HttpCall;
import com.lms.BI.Pojo.LoginCredentails;
import com.lms.BI.Service.BorrowerDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BorrowerDetailsController {
    
    @Autowired
    BorrowerDetailsService borrowerDetailsService;

    @GetMapping("/user-by-id/{id}")
    public ResponseEntity<BorrowerDetails> getById(@PathVariable int id) {

        try {
            return new ResponseEntity<>(borrowerDetailsService.getById(id), HttpStatus.OK);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user-by-uname/{uname}")
    public ResponseEntity<BorrowerDetails> getByUname(@PathVariable String uname) {

        try {
            return new ResponseEntity<>(borrowerDetailsService.getByUname(uname), HttpStatus.OK);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all-user")
    public ResponseEntity<List<BorrowerDetails>> getAllUsers() {

        try {
            return new ResponseEntity<>(borrowerDetailsService.getAllDetails(), HttpStatus.OK);
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

    @PostMapping("/login-call")
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

    @PostMapping("/login")
    public LoginCredentails login(@RequestBody String uname) {

        LoginCredentails loginCredentails = new LoginCredentails();
        BorrowerDetails borrowerDetails = borrowerDetailsService.getByUname(loginCredentails.getUname());
        loginCredentails.setUname(borrowerDetails.getUname());
        loginCredentails.setPassword(borrowerDetails.getPassword());
        return loginCredentails;
    }

    @DeleteMapping("/delete-by-id/{id}")
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

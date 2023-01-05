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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.lms.BI.Model.BorrowerDetails;
import com.lms.BI.Pojo.HttpCall;
import com.lms.BI.Pojo.LoginCredentails;
import com.lms.BI.Service.BorrowerDetailsService;
import com.lms.BI.Service.TokenService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8083"})
public class BorrowerDetailsController {
    
    @Autowired
    BorrowerDetailsService borrowerDetailsService;

    @Autowired
    TokenService tokenService;

    @GetMapping("/user-by-id/{id}")
    public ResponseEntity<BorrowerDetails> getById(@RequestHeader("Authorization") String token, 
                                                    @PathVariable int id) {

        if(tokenService.checkValidation(token)) {
            try {
                return new ResponseEntity<>(borrowerDetailsService.getById(id), HttpStatus.OK);
            }catch(IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/user-by-uname/{uname}")
    public ResponseEntity<BorrowerDetails> getByUname(@RequestHeader("Authorization") String token, 
                                                        @PathVariable String uname) {

        if(tokenService.checkValidation(token)) {
            try {
                return new ResponseEntity<>(borrowerDetailsService.getByUname(uname), HttpStatus.OK);
            }catch(IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/all-user")
    public ResponseEntity<List<BorrowerDetails>> getAllUsers(@RequestHeader("Authorization") String token) {

        if(tokenService.checkValidation(token)) {
            try {
                return new ResponseEntity<>(borrowerDetailsService.getAllDetails(), HttpStatus.OK);
            }catch(NullPointerException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<BorrowerDetails> saveEntity(@RequestBody BorrowerDetails nBorrowerDetails) {

        try {
            return new ResponseEntity<>(borrowerDetailsService.saveBorrowerDetail(nBorrowerDetails), 
                                        HttpStatus.CREATED);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public LoginCredentails login(@RequestBody String uname) {

        LoginCredentails loginCredentails = new LoginCredentails();
        BorrowerDetails borrowerDetails = borrowerDetailsService.getByUname(uname);
        loginCredentails.setUname(borrowerDetails.getUname());
        loginCredentails.setPassword(borrowerDetails.getPassword());
        return loginCredentails;
    }

    @PutMapping("/update")
    public ResponseEntity<BorrowerDetails> updateEntity(@RequestHeader("Authorization") String token, 
                                                        @RequestBody BorrowerDetails borrowerDetails) {

        if(tokenService.checkValidation(token)) {
            try {
                return new ResponseEntity<>(borrowerDetailsService.updateBorrowerDetail(borrowerDetails), 
                                            HttpStatus.OK);
            }catch(Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<HttpCall> deletEntity(@RequestHeader("Authorization") String token, 
                                                @PathVariable int id) {

        if(tokenService.checkValidation(token)) {
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
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}

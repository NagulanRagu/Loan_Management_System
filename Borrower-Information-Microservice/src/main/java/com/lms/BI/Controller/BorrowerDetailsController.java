package com.lms.BI.Controller;

import java.util.stream.Collectors;

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
import com.lms.BI.Model.Role;
import com.lms.BI.Pojo.HttpCall;
import com.lms.BI.Pojo.LoginCredentails;
import com.lms.BI.Service.BorrowerDetailsService;
import com.lms.BI.Service.TokenService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8083" })
public class BorrowerDetailsController {

    @Autowired
    BorrowerDetailsService borrowerDetailsService;

    @Autowired
    TokenService tokenService;

    @GetMapping("/user-by-id/{id}")
    public ResponseEntity<?> getById(@RequestHeader("Authorization") String token, @PathVariable int id) {

        if (tokenService.checkValidation(token)) {
            try {
                return new ResponseEntity<>(borrowerDetailsService.getById(id), HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/user-by-uname/{uname}")
    public ResponseEntity<?> getByUname(@RequestHeader("Authorization") String token, @PathVariable String uname) {

        if (tokenService.checkValidation(token)) {
            try {
                return new ResponseEntity<>(borrowerDetailsService.getByUname(uname), HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/all-user")
    public ResponseEntity<?> getAllUsers(@RequestHeader("Authorization") String token) {

        if (tokenService.checkValidation(token)) {
            try {
                return new ResponseEntity<>(borrowerDetailsService.getAllDetails(), HttpStatus.OK);
            } catch (NullPointerException e) {
                return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/user-by-uname")
    public ResponseEntity<Boolean> checkingUniqueUsername(@RequestBody String uname) {

        return new ResponseEntity<>(borrowerDetailsService.checkUname(uname), HttpStatus.OK);
    }

    @PostMapping("/user-by-emailId")
    public ResponseEntity<Boolean> checkingUniqueEmailId(@RequestBody String emailId) {

        return new ResponseEntity<>(borrowerDetailsService.checkEmailId(emailId), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> saveEntity(@RequestBody BorrowerDetails nBorrowerDetails) {
    	try {
            return new ResponseEntity<>(borrowerDetailsService.saveBorrowerDetail(nBorrowerDetails), HttpStatus.CREATED);
    	}catch(Exception e) {
    		return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    	}

    }

    @PostMapping("/login")
    public ResponseEntity<LoginCredentails> login(@RequestBody String uname) {

        LoginCredentails loginCredentails = new LoginCredentails();
        try {
            BorrowerDetails borrowerDetails = borrowerDetailsService.getByUname(uname);
            loginCredentails.setUname(borrowerDetails.getUname());
            loginCredentails.setPassword(borrowerDetails.getPassword());
            loginCredentails.setRole(borrowerDetails.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
            return new ResponseEntity<>(loginCredentails, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            loginCredentails.setUname(e.getLocalizedMessage());
            return new ResponseEntity<>(loginCredentails, HttpStatus.OK);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEntity(@RequestHeader("Authorization") String token,
            @RequestBody BorrowerDetails borrowerDetails) {

        if (tokenService.checkValidation(token)) {
            return new ResponseEntity<>(borrowerDetailsService.updateBorrowerDetail(borrowerDetails), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<HttpCall> deletEntity(@RequestHeader("Authorization") String token, @PathVariable int id) {

        if (tokenService.checkValidation(token)) {
            HttpCall httpCall = new HttpCall();
            httpCall.setRequest("Delete call is Requested.");
            try {
                borrowerDetailsService.deleteBorrowerDetail(id);
                httpCall.setReponse("Details is Deleted.");
                return new ResponseEntity<>(httpCall, HttpStatus.OK);
            } catch (Exception e) {
                httpCall.setReponse("Error occured in Deleting.");
                return new ResponseEntity<>(httpCall, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}

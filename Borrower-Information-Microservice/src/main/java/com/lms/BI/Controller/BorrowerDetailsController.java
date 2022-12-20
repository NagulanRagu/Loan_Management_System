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
import com.lms.BI.Service.BorrowerDetailsService;

@RestController
@RequestMapping("/user")
public class BorrowerDetailsController {
    
    @Autowired
    BorrowerDetailsService borrowerDetailsService;

    @GetMapping("/{id}")
    public ResponseEntity<BorrowerDetails> getById(@PathVariable int id) {

        try {
            return new ResponseEntity<>(borrowerDetailsService.getById(id), HttpStatus.OK);
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

    @PostMapping("/")
    public ResponseEntity<?> saveEntity(@RequestBody BorrowerDetails nBorrowerDetails) {

        try {
            return new ResponseEntity<>(borrowerDetailsService.saveBorrowerDetail(nBorrowerDetails), HttpStatus.CREATED);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<String>("An error occured in saving.", HttpStatus.INTERNAL_SERVER_ERROR);
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

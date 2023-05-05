package com.lms.AM.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.lms.AM.FeignClient.AuthClient;
import com.lms.AM.Pojo.AuthResponse;
import com.lms.AM.Pojo.JwtResponse;
import com.lms.AM.Pojo.LoginCredentails;
import com.lms.AM.Service.UserService;
import com.lms.AM.Utility.JWTUtility;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    /**
     * This is a private field of type {@link JwtUtil} class which provides the
     * utilities for the token like get token, validate token, expiration time etc.
     */
    @Autowired
    JWTUtility jwtUtility;

    @Autowired
    AuthClient authClient;

    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * This is a private field of type {@link ManagerDetailsService} class which is
     * used to fetch the user credentials from the database
     */
    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public String healthCheck() {
        return "Authentication Microservice working";
    }

    /**
     * This method is used to check the credentials whether the provided credentials
     * are correct or not. For this we will call authenticate method. If user
     * credentials are correct then we will fetch the data from database based on
     * userid and return it to the user with a token
     * 
     * 
     */

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginCredentails loginCredentails) {
        
        try{
            final UserDetails userDetails = userService.loadUserByUsername(loginCredentails.getUname());
            if (userDetails.getPassword().equals(loginCredentails.getPassword())) {
                final String token = jwtUtility.generateToken(userDetails);
                final List<String> roles = authClient.login(loginCredentails.getUname()).getBody().getRole();
                return new ResponseEntity<>(new JwtResponse(token, roles), HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Password is Wrong", HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<AuthResponse> getValidity(@RequestHeader("Authorization") String token) {
        AuthResponse authResponse = new AuthResponse();
        token = token.substring(7);
        try {
            if (jwtUtility.validateToken(token)) {
                authResponse.setUserId(jwtUtility.extractUsername(token));
                authResponse.setValid(true);
                authResponse.setMessage("Token is Good.");
            }
        } catch (Exception e) {
            authResponse.setValid(false);
            authResponse.setMessage("Token is not valid.");
            return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    }
}
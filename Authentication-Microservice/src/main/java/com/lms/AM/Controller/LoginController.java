package com.lms.AM.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.AM.Pojo.JwtResponse;
import com.lms.AM.Pojo.LoginCredentails;
import com.lms.AM.Service.UserService;
import com.lms.AM.Utility.JWTUtility;

@RestController
public class LoginController {

    @Autowired
    JWTUtility jwtUtility;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;
    
    @GetMapping("/")
    public String healthCheck(){
        return "Authentication Microservice working";
    }

    @PostMapping("/authenticates")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody LoginCredentails loginCredentails) throws BadCredentialsException {
        
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginCredentails.getUname(), loginCredentails.getPassword())
            );
        }catch(BadCredentialsException e) {
            throw new BadCredentialsException("Invalid_Credentails", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(loginCredentails.getUname());
        final String token = jwtUtility.generateToken(userDetails);
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }
}

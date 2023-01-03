package com.lms.RI.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.RI.FeignClient.FeignAuthorization;
import com.lms.RI.Pojo.AuthResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TokenService {
    
    @Autowired
    FeignAuthorization feignAuthorization;

    public boolean checkValidation(String token) {

        log.info("Checking the Token Validation for Token: {}", token);
        AuthResponse authResponse = feignAuthorization.getValidity(token).getBody();
        if(authResponse.isValid()) {
            log.info("Token is Valid.");
            return true;
        }else {
            log.error("Token is not valid.");
            return false;
        }
    }
}

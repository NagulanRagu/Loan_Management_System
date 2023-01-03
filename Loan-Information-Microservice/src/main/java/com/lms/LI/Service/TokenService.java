package com.lms.LI.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.LI.FeignClient.FeignAuthorization;
import com.lms.LI.Pojo.AuthResponse;

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

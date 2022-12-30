package com.lms.AM.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    
    @GetMapping("/")
    public String HealthCheck(){
        return "Authentication Microservice working";
    }
}

package com.lms.AM.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lms.AM.Pojo.LoginCredentails;

@FeignClient(url = "http://localhost:8080/", name = "auth")
public interface AuthClient {
    
    @PostMapping("/login")
    public ResponseEntity<LoginCredentails> login(@RequestBody String uname);
}

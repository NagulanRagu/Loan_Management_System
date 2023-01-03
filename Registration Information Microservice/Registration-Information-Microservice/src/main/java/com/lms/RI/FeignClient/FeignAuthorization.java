package com.lms.RI.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.lms.RI.Pojo.AuthResponse;

@FeignClient(url = "http://localhost:8083/", name = "auth")
public interface FeignAuthorization {
    
    @GetMapping("/validate")
    public ResponseEntity<AuthResponse> getValidity(@RequestHeader("Authorization") String token);
}


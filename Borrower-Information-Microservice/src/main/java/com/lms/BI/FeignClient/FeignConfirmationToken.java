package com.lms.BI.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8092/", name = "confirmationToken")
public interface FeignConfirmationToken {

	@GetMapping("/token/create/{borrowerName}")
	public ResponseEntity<String> createToken(@PathVariable String borrowerName); 
}

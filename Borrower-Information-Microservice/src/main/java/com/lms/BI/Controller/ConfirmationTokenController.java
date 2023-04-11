package com.lms.BI.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lms.BI.Service.ConfirmationTokenService;


@RestController
public class ConfirmationTokenController {

	@Autowired
	ConfirmationTokenService confirmationTokenService;
	
	@GetMapping("/token/confirm/{token}")
	public ResponseEntity<?> confirmToken(@PathVariable String token) {
		try {
			String confirmation = confirmationTokenService.confirmToken(token);
			return new ResponseEntity<>(confirmation, HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}

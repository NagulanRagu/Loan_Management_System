package com.lms.RI.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lms.RI.FeignClient.FeignAuthorization;
import com.lms.RI.Pojo.AuthResponse;
import com.lms.RI.Service.TokenService;

@SpringBootTest
public class TokenServiceTest {

	@Mock
	FeignAuthorization feignAuthorization;

	@InjectMocks
	TokenService tokenService;

	@Test
	public void testCheckValidationIsValid() {
		ResponseEntity<AuthResponse> entity = new ResponseEntity<AuthResponse>(
				new AuthResponse("Nagulan R U", true, "Token is Good"), HttpStatus.OK);
		when(feignAuthorization.getValidity("Token")).thenReturn(entity);
		assertEquals(true, tokenService.checkValidation("Token"));
	}

	@Test
	public void testCheckValidationIsNotValid() {
		ResponseEntity<AuthResponse> entity = new ResponseEntity<AuthResponse>(
				new AuthResponse("Nagulan R U", false, "Token is not Valid"), HttpStatus.FORBIDDEN);
		when(feignAuthorization.getValidity("Token")).thenReturn(entity);
		assertEquals(false, tokenService.checkValidation("Token"));
	}
}

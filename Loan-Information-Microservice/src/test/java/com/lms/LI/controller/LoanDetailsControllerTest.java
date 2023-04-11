package com.lms.LI.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.lms.LI.Controller.LoanDetailsController;
import com.lms.LI.Model.LoanDetails;
import com.lms.LI.Service.LoanDetailsService;
import com.lms.LI.Service.TokenService;

@ContextConfiguration
@SpringBootTest
public class LoanDetailsControllerTest {

	@Mock
	LoanDetailsService loanDetailsService;

	@Mock
	TokenService tokenService;

	@InjectMocks
	LoanDetailsController loanDetailsController;

	@Test
	public void testGetAllDetails() {
		List<LoanDetails> loanDetails = new ArrayList<>();
		loanDetails.add(new LoanDetails(1, "L1001", "Personal", 16));
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanDetailsService.getAllDetails()).thenReturn(loanDetails);
		assertEquals(new ResponseEntity<>(loanDetails, HttpStatus.OK), loanDetailsController.getAllDetails("Token"));
	}

	@Test
	public void testGetAllDetailsException() {
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanDetailsService.getAllDetails()).thenThrow(new NullPointerException("No Details Present"));
		assertEquals(new ResponseEntity<>("No Details Present", HttpStatus.NOT_FOUND),
				loanDetailsController.getAllDetails("Token"));
	}

	@Test
	public void testGetAllDetailsTokenNotValid() {
		when(tokenService.checkValidation("Token")).thenReturn(false);
		assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), loanDetailsController.getAllDetails("Token"));
	}

	@Test
	public void testGetById() {
		LoanDetails loanDetails = new LoanDetails(1, "L1001", "Personal", 16);
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanDetailsService.getById(1)).thenReturn(loanDetails);
		assertEquals(new ResponseEntity<>(loanDetails, HttpStatus.OK), loanDetailsController.getById("Token", 1));
	}

	@Test
	public void testGetByIdException() {
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanDetailsService.getById(1)).thenThrow(new IllegalArgumentException("No Details is found for Loan id."));
		assertEquals(new ResponseEntity<>("No Details is found for Loan id.", HttpStatus.NOT_FOUND),
				loanDetailsController.getById("Token", 1));
	}

	@Test
	public void testGetByIdTokenNotValid() {
		when(tokenService.checkValidation("Token")).thenReturn(false);
		assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), loanDetailsController.getById("Token", 1));
	}

	@Test
	public void testGetByType() {
		LoanDetails loanDetails = new LoanDetails(1, "L1001", "Personal", 16);
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanDetailsService.getByType("Personal")).thenReturn(loanDetails);
		assertEquals(new ResponseEntity<>(loanDetails, HttpStatus.OK),
				loanDetailsController.getByType("Token", "Personal"));
	}

	@Test
	public void testGetByTypeException() {
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanDetailsService.getByType("Personal"))
				.thenThrow(new IllegalArgumentException("No Details is found for Loan Type"));
		assertEquals(new ResponseEntity<>("No Details is found for Loan Type", HttpStatus.NOT_FOUND),
				loanDetailsController.getByType("Token", "Personal"));
	}

	@Test
	public void testGetByTypeTokenNotValid() {
		when(tokenService.checkValidation("Token")).thenReturn(false);
		assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), loanDetailsController.getByType("Token", "Personal"));
	}

	@Test
	public void testSaveEntity() {
		LoanDetails loanDetails = new LoanDetails(1, "L1001", "Personal", 16);
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanDetailsService.saveDetails(loanDetails)).thenReturn(loanDetails);
		assertEquals(new ResponseEntity<>(loanDetails, HttpStatus.CREATED),
				loanDetailsController.saveEntity("Token", loanDetails));
	}

	@Test
	public void testSaveEntityTokenNotValid() {
		when(tokenService.checkValidation("Token")).thenReturn(false);
		assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN),
				loanDetailsController.saveEntity("Token", new LoanDetails()));
	}

	@Test
	public void testUpdateEntity() {
		LoanDetails loanDetails = new LoanDetails(1, "L1001", "Personal", 16);
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanDetailsService.updateDetails(loanDetails)).thenReturn(loanDetails);
		assertEquals(new ResponseEntity<>(loanDetails, HttpStatus.OK),
				loanDetailsController.updateEntity("Token", loanDetails));
	}

	@Test
	public void testUpdateEntityTokenNotValid() {
		when(tokenService.checkValidation("Token")).thenReturn(false);
		assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN),
				loanDetailsController.updateEntity("Token", new LoanDetails()));
	}
}

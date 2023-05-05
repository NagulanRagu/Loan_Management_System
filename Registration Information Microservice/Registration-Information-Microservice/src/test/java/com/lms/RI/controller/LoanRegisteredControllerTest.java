package com.lms.RI.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lms.RI.Controller.LoanRegisteredController;
import com.lms.RI.Model.GuarantorInfo;
import com.lms.RI.Model.LoanRegistered;
import com.lms.RI.Service.LoanRegisteredService;
import com.lms.RI.Service.TokenService;

@SpringBootTest
public class LoanRegisteredControllerTest {

	@Mock
	LoanRegisteredService loanRegisteredService;
	
	@Mock
	TokenService tokenService;
	
	@InjectMocks
	LoanRegisteredController loanRegisteredController;
	
	public LoanRegistered getLoanRegistered() {
		LoanRegistered loanRegistered = new LoanRegistered(1, "Nagulan R U", "Personal", "4,00,000", "4,00,000", 60,
				"11,000", new Date(), new GuarantorInfo(), "Accepted");
		return loanRegistered;
	}
	
	@Test
	public void testGetAllDetails() {
		List<LoanRegistered> loanRegistered = new ArrayList<>();
		loanRegistered.add(getLoanRegistered());
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanRegisteredService.getAllDetails()).thenReturn(loanRegistered);
		assertEquals(new ResponseEntity<>(loanRegistered, HttpStatus.OK), loanRegisteredController.getAllDetails("Token"));
	}
	
	@Test
	public void testGetAllDetailsNullPointerException() {
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanRegisteredService.getAllDetails()).thenThrow(new NullPointerException("No Details Present in Database"));
		assertEquals(new ResponseEntity<>("No Details Present in Database", HttpStatus.NOT_FOUND), loanRegisteredController.getAllDetails("Token"));
	}
	
	@Test
	public void testGetAllDetailsTokenValidationFails() {
		when(tokenService.checkValidation("Token")).thenReturn(false);
		assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), loanRegisteredController.getAllDetails("Token"));
	}
	
	@Test
	public void testGetByLoanId() {
		LoanRegistered loanRegistered = getLoanRegistered();
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanRegisteredService.getById(1)).thenReturn(loanRegistered);
		assertEquals(new ResponseEntity<>(loanRegistered, HttpStatus.OK), loanRegisteredController.getByLoanId("Token", 1));
	}
	
	@Test
	public void testGetByLoanIdNullPointerException() {
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanRegisteredService.getById(1)).thenThrow(new IllegalArgumentException("No Detail found for the Loan Id: " + 1));
		assertEquals(new ResponseEntity<>("No Detail found for the Loan Id: " + 1, HttpStatus.NOT_FOUND), loanRegisteredController.getByLoanId("Token", 1));
	}
	
	@Test
	public void testGetByLoanIdTokenValidationFails() {
		when(tokenService.checkValidation("Token")).thenReturn(false);
		assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), loanRegisteredController.getByLoanId("Token", 1));
	}
	
	@Test
	public void testGetByBorrowerId() {
		List<LoanRegistered> loanRegistered = new ArrayList<>();
		loanRegistered.add(getLoanRegistered());
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanRegisteredService.getByBorrowerName("Nagulan R U")).thenReturn(loanRegistered);
		assertEquals(new ResponseEntity<>(loanRegistered, HttpStatus.OK), loanRegisteredController.getByBorrowerId("Token", "Nagulan R U"));
	}
	
	@Test
	public void testGetByBorrowerIdNullPointerException() {
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanRegisteredService.getByBorrowerName("Nagulan R U")).thenThrow(new IllegalArgumentException("No Details found for Borrower Name: " + "Nagulan R U"));
		assertEquals(new ResponseEntity<>("No Details found for Borrower Name: " + "Nagulan R U", HttpStatus.NOT_FOUND), loanRegisteredController.getByBorrowerId("Token", "Nagulan R U"));
	}
	
	@Test
	public void testGetByBorrowerIdTokenValidationFails() {
		when(tokenService.checkValidation("Token")).thenReturn(false);
		assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), loanRegisteredController.getByBorrowerId("Token", "Nagulan R U"));
	}
	
	@Test
	public void testSaveEntity() {
		LoanRegistered loanRegistered = getLoanRegistered();
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanRegisteredService.saveDetails(loanRegistered)).thenReturn(loanRegistered);
		assertEquals(new ResponseEntity<>(loanRegistered, HttpStatus.CREATED), loanRegisteredController.saveEntity("Token", loanRegistered));
	}
	
	@Test
	public void testSaveEntityException() {
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanRegisteredService.saveDetails(new LoanRegistered())).thenThrow(new IllegalStateException());
		assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), loanRegisteredController.saveEntity("Token", new LoanRegistered()));
	}
	
	@Test
	public void testSaveEntityTokenValidationFails() {
		when(tokenService.checkValidation("Token")).thenReturn(false);
		assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), loanRegisteredController.saveEntity("Token", new LoanRegistered()));
	}
	
	@Test
	public void testUpdateEntity() {
		LoanRegistered loanRegistered = getLoanRegistered();
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanRegisteredService.saveDetails(loanRegistered)).thenReturn(loanRegistered);
		assertEquals(new ResponseEntity<>(loanRegistered, HttpStatus.CREATED), loanRegisteredController.saveEntity("Token", loanRegistered));
	}
	
	@Test
	public void testUpdateEntityException() {
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(loanRegisteredService.saveDetails(new LoanRegistered())).thenThrow(new IllegalStateException());
		assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), loanRegisteredController.saveEntity("Token", new LoanRegistered()));
	}
	
	@Test
	public void testUpdateEntityTokenValidationFails() {
		when(tokenService.checkValidation("Token")).thenReturn(false);
		assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), loanRegisteredController.saveEntity("Token", new LoanRegistered()));
	}
}

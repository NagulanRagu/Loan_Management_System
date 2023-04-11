package com.lms.LI.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.lms.LI.Model.LoanDetails;
import com.lms.LI.Repository.LoanDetailsRepository;
import com.lms.LI.Service.LoanDetailsService;

@ContextConfiguration
@SpringBootTest
public class LoanDetailsServiceTest {

	@Mock
	LoanDetailsRepository loanDetailsRepository;
	
	@InjectMocks
	LoanDetailsService loanDetailsService;
	
	@Test
	public void testGetAllDetails() {
		List<LoanDetails> loanDetails = new ArrayList<>();
		loanDetails.add(new LoanDetails(1, "L1001", "Personal", 16));
		when(loanDetailsRepository.findAll()).thenReturn(loanDetails);
		assertEquals(loanDetails, loanDetailsService.getAllDetails());
	}
	
	@Test
	public void testGetAllDetailsNullPointerExcption() {
		when(loanDetailsRepository.findAll()).thenReturn(null);
		assertThrows(NullPointerException.class, () -> loanDetailsService.getAllDetails());
	}
	
	@Test
	public void testGetById() {
		LoanDetails loanDetails = new LoanDetails(1, "L1001", "Personal", 16);
		when(loanDetailsRepository.findById(1)).thenReturn(loanDetails);
		assertEquals(loanDetails, loanDetailsService.getById(1));
	}
	
	@Test
	public void testGetByIdNullPointerExcption() {
		when(loanDetailsRepository.findById(1)).thenReturn(null);
		assertThrows(IllegalArgumentException.class, () -> loanDetailsService.getById(1));
	}
	
	@Test
	public void testGetByLoanType() {
		LoanDetails loanDetails = new LoanDetails(1, "L1001", "Personal", 16);
		when(loanDetailsRepository.findByLoanType("Personal")).thenReturn(loanDetails);
		assertEquals(loanDetails, loanDetailsService.getByType("Personal"));
	}
	
	@Test
	public void testGetByLoanTypeNullPointerExcption() {
		when(loanDetailsRepository.findByLoanType("Personal")).thenReturn(null);
		assertThrows(IllegalArgumentException.class, () -> loanDetailsService.getByType("Personal"));
	}
	
	@Test
	public void testSaveDetails() {
		LoanDetails loanDetails = new LoanDetails(1, "L1001", "Personal", 16);
		when(loanDetailsRepository.save(loanDetails)).thenReturn(loanDetails);
		assertEquals(loanDetails, loanDetailsService.saveDetails(loanDetails));
	}
}

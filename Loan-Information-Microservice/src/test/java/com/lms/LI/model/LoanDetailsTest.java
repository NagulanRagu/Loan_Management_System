package com.lms.LI.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.lms.LI.Model.LoanDetails;

@ContextConfiguration
@SpringBootTest
public class LoanDetailsTest {

	@InjectMocks
	LoanDetails loanDetails = new LoanDetails();
	
	@Test
	public void testNoArgsContructor() {
		assertNotNull(loanDetails);
	}
	
	@Test
	public void testAllArgsContructor() {
		LoanDetails loanDetails = new LoanDetails(1, "L1001", "Personal", 16);
		assertEquals(1, loanDetails.getId());
	}
	
	@Test
	public void testParatentizedContructor() {
		LoanDetails loanDetails = new LoanDetails("L1001", "Personal", 16);
		assertEquals(16, loanDetails.getRateOfInterest());
	}
	
	@Test
	public void testId() {
		loanDetails.setId(1);
		assertEquals(1, loanDetails.getId());
	}
	
	@Test
	public void testLoanNo() {
		loanDetails.setLoanNo("L1001");
		assertEquals("L1001", loanDetails.getLoanNo());
	}
	
	@Test
	public void testLoanType() {
		loanDetails.setLoanType("Personal");
		assertEquals("Personal", loanDetails.getLoanType());
	}
	
	@Test
	public void testRateOfInterest() {
		loanDetails.setRateOfInterest(16);
		assertEquals(16, loanDetails.getRateOfInterest());
	}
}

package com.lms.RI.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.lms.RI.Model.GuarantorAddress;
import com.lms.RI.Model.GuarantorInfo;
import com.lms.RI.Model.LoanRegistered;

@SpringBootTest
public class LoanRegisteredTest {

	@InjectMocks
	LoanRegistered loanRegistered = new LoanRegistered();

	@Test
	public void testNoArgsConstructor() {
		assertNotNull(loanRegistered);
	}

	@Test
	public void testAllArgsConstructor() {
		LoanRegistered loanRegistered = new LoanRegistered(1, "Nagulan R U", "Personal", "4,00,000", "4,00,000", 60,
				"11,000", new Date(), new GuarantorInfo(), "Accepted");
		assertEquals(1, loanRegistered.getId());
	}

	@Test
	public void testParantizedConstructor() {
		LoanRegistered loanRegistered = new LoanRegistered("Nagulan R U", "Personal", "4,00,000", "4,00,000", 60,
				"11,000", new Date(), new GuarantorInfo(), "Accepted");
		assertEquals("Nagulan R U", loanRegistered.getBorrowerName());
	}

	@Test
	public void testId() {
		loanRegistered.setId(1);
		assertEquals(1, loanRegistered.getId());
	}

	@Test
	public void testBorrowerName() {
		loanRegistered.setBorrowerName("Nagulan R U");
		assertEquals("Nagulan R U", loanRegistered.getBorrowerName());
	}

	@Test
	public void testLoanType() {
		loanRegistered.setLoanType("Personal");
		assertEquals("Personal", loanRegistered.getLoanType());
	}

	@Test
	public void testAskedLoanAmt() {
		loanRegistered.setAskedLoanAmt("4,00,000");
		assertEquals("4,00,000", loanRegistered.getAskedLoanAmt());
	}

	@Test
	public void testProvidedLoanAmt() {
		loanRegistered.setProvidedLoanAmt("4,00,000");
		assertEquals("4,00,000", loanRegistered.getProvidedLoanAmt());
	}

	@Test
	public void testPaymentPeriod() {
		loanRegistered.setPaymentPeriod(60);
		assertEquals(60, loanRegistered.getPaymentPeriod());
	}

	@Test
	public void testEmiAmt() {
		loanRegistered.setEmiAmt("11,000");
		assertEquals("11,000", loanRegistered.getEmiAmt());
	}

	@Test
	public void testIssueDate() {
		loanRegistered.setIssuedDate(new Date());
		assertEquals(new Date(), loanRegistered.getIssuedDate());
	}

	@Test
	public void testGuarantorInfo() {
		loanRegistered.setGuarantorInfo(
				new GuarantorInfo(1, "Ravikumar K", "8870323658", "ravikumaravel00@gmail.com", new GuarantorAddress()));
		assertEquals("Ravikumar K", loanRegistered.getGuarantorInfo().getGuarantorName());
	}

	@Test
	public void testStatus() {
		loanRegistered.setStatus("Accepted");
		assertEquals("Accepted", loanRegistered.getStatus());
	}
}

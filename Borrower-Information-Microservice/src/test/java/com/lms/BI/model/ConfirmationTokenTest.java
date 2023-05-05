package com.lms.BI.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.lms.BI.Model.BorrowerDetails;
import com.lms.BI.Model.ConfirmationToken;

@ContextConfiguration
@SpringBootTest
public class ConfirmationTokenTest {

	@InjectMocks
	ConfirmationToken confirmationToken = new ConfirmationToken();
	
	@Test
	public void testNoArgsConstructor() {
		assertNotNull(confirmationToken);
	}
	
	@Test
	public void testAllArgsConstructor() {
		ConfirmationToken confirmationToken = new ConfirmationToken(1, "Token", LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), LocalDateTime.now().plusMinutes(5), new BorrowerDetails());
		assertEquals(1, confirmationToken.getId());
	}
	
	@Test
	public void testParatenizedConstructor() {
		ConfirmationToken confirmationToken = new ConfirmationToken("Token", LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), new BorrowerDetails());
		assertEquals("Token", confirmationToken.getToken());
	}
	
	@Test
	public void testId() {
		confirmationToken.setId(1);
		assertEquals(1, confirmationToken.getId());
	}
	
	@Test
	public void testToken() {
		confirmationToken.setToken("Token");
		assertEquals("Token", confirmationToken.getToken());
	}
	
	@Test
	public void testCreatedAt() {
		confirmationToken.setCreatedAt(LocalDateTime.now());;
		assertEquals(LocalDateTime.now(), confirmationToken.getCreatedAt());
	}
	
	@Test
	public void testExpiredAt() {
		confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));;
		assertEquals(LocalDateTime.now().plusMinutes(15), confirmationToken.getExpiresAt());
	}
	
	@Test
	public void testConfirmedAt() {
		confirmationToken.setConfirmedAt(LocalDateTime.now().plusMinutes(5));;
		assertEquals(LocalDateTime.now().plusMinutes(5), confirmationToken.getConfirmedAt());
	}
	
	@Test
	public void testBorrowerDetails() {
		confirmationToken.setBorrowerDetails(new BorrowerDetails());
		assertEquals(new BorrowerDetails(), confirmationToken.getBorrowerDetails());
	}
}

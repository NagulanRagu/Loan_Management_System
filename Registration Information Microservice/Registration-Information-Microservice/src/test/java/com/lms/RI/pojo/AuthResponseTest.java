package com.lms.RI.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.lms.RI.Pojo.AuthResponse;

@SpringBootTest
public class AuthResponseTest {

	@InjectMocks
	AuthResponse authResponse = new AuthResponse();
	
	@Test
	public void testNoArgsConstructor() {
		assertNotNull(authResponse);
	}
	
	@Test
	public void testAllArgsConstructor() {
		AuthResponse authResponse = new AuthResponse("Nagulan R U", true, "Token is Good");
		assertEquals(true, authResponse.isValid());
	}
	
	@Test
	public void testUserId() {
		authResponse.setUserId("Nagulan R U");
		assertEquals("Nagulan R U", authResponse.getUserId());
	}
	
	@Test
	public void testIsValid() {
		authResponse.setValid(true);
		assertEquals(true, authResponse.isValid());
	}
	
	@Test
	public void testMessage() {
		authResponse.setMessage("Token is Good");
		assertEquals("Token is Good", authResponse.getMessage());
	}
}

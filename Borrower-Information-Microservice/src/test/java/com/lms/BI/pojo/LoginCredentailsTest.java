package com.lms.BI.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.lms.BI.Pojo.LoginCredentails;

@ContextConfiguration
@SpringBootTest
public class LoginCredentailsTest {

	@InjectMocks
	LoginCredentails loginCredentails = new LoginCredentails();
	
	@Test
	public void testNoArgsContructor() {
		assertNotNull(loginCredentails);
	}
	
	@Test
	public void testAllArgsContructor() {
		LoginCredentails loginCredentails = new LoginCredentails("Nagulan R U", "1234", new ArrayList<String>());
		assertEquals("Nagulan R U", loginCredentails.getUname());
	}
	
	@Test
	public void testUname() {
		loginCredentails.setUname("Nagulan R U");
		assertEquals("Nagulan R U", loginCredentails.getUname());
	}
	
	@Test
	public void testPassword() {
		loginCredentails.setPassword("1234");
		assertEquals("1234", loginCredentails.getPassword());
	}
	
	@Test
	public void testRoles() {
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_USER");
		roles.add("ROLE_ADMIN");
		loginCredentails.setRole(roles);
		assertEquals(roles, loginCredentails.getRole());
	}
}

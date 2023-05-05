package com.lms.BI.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.lms.BI.Model.Role;

@ContextConfiguration
@SpringBootTest
public class RoleTest {

	@InjectMocks
	Role role = new Role();
	
	@Test
	public void testNoArgsConstructor() {
		assertNotNull(role);
	}
	
	@Test
	public void testAllArgsConstructor() {
		Role role = new Role(1, "ROLE_ADMIN");
		assertEquals(1, role.getId());
	}
	
	@Test
	public void testParatenized() {
		Role role = new Role("ROLE_ADMIN");
		assertEquals("ROLE_ADMIN", role.getName());
	}
	
	@Test
	public void testId() {
		role.setId(1);
		assertEquals(1, role.getId());
	}
	
	@Test
	public void testName() {
		role.setName("ROLE_ADMIN");
		assertEquals("ROLE_ADMIN", role.getName());
	}
}

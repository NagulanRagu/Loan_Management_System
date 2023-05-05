package com.lms.BI.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.lms.BI.Model.Address;
import com.lms.BI.Model.BorrowerDetails;
import com.lms.BI.Model.Gender;
import com.lms.BI.Model.PersonalInformation;
import com.lms.BI.Model.Role;

@ContextConfiguration
@SpringBootTest
public class BorrowerDetailsTest {

	@InjectMocks
	BorrowerDetails borrowerDetails = new BorrowerDetails();

	@Test
	public void testNoArgConstructor() {
		assertNotNull(borrowerDetails);
	}

	@Test
	public void testAllArgConstructor() {
		BorrowerDetails borrowerDetails = new BorrowerDetails(1, new PersonalInformation(), "Nagulan R U", "1234",
				"8870323658", "runagulan88@gmail.com", new Address(), new HashSet<Role>(), true);
		assertEquals(1, borrowerDetails.getId());
	}

	@Test
	public void testParamenterizedConstructor() {
		BorrowerDetails borrowerDetails = new BorrowerDetails(new PersonalInformation(), "Nagulan R U", "1234",
				"8870323658", "runagulan88@gmail.com", new Address(), new HashSet<Role>(), true);
		assertEquals("Nagulan R U", borrowerDetails.getUname());
	}

	@Test
	public void testId() {
		borrowerDetails.setId(1);
		assertEquals(1, borrowerDetails.getId());
	}

	@Test
	public void testPersonalInformation() {
		borrowerDetails.setPersonalInformation(
				new PersonalInformation("Nagulan", "R U", new Date(), Gender.Male, "6101 8953 1282", "AIUPU9900B"));
		assertEquals(new PersonalInformation("Nagulan", "R U", new Date(), Gender.Male, "6101 8953 1282", "AIUPU9900B"),
				borrowerDetails.getPersonalInformation());
	}

	@Test
	public void testUname() {
		borrowerDetails.setUname("Nagulan R U");
		assertEquals("Nagulan R U", borrowerDetails.getUname());
	}

	@Test
	public void testPassword() {
		borrowerDetails.setPassword("1234");
		assertEquals("1234", borrowerDetails.getPassword());
	}

	@Test
	public void testPhonono() {
		borrowerDetails.setPhoneno("8870323658");
		assertEquals("8870323658", borrowerDetails.getPhoneno());
	}

	@Test
	public void testEmailId() {
		borrowerDetails.setEmailId("runagulan88@gmail.com");
		assertEquals("runagulan88@gmail.com", borrowerDetails.getEmailId());
	}

	@Test
	public void testAddress() {
		borrowerDetails.setBorrowerAddress(
				new Address("63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001"));
		assertEquals(new Address("63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001"),
				borrowerDetails.getBorrowerAddress());
	}

	@Test
	public void testRoles() {
		Set<Role> role = new HashSet<Role>();
		role.add(new Role("USER_ADMIN"));
		borrowerDetails.setRoles(role);
		assertEquals(role, borrowerDetails.getRoles());
	}
	
	@Test
	public void testEnabled() {
		borrowerDetails.setEnabled(true);
		assertEquals(true, borrowerDetails.isEnabled());
	}
}

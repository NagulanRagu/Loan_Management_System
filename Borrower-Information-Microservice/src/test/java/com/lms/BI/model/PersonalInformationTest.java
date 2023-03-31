package com.lms.BI.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.lms.BI.Model.Gender;
import com.lms.BI.Model.PersonalInformation;

@ContextConfiguration
@SpringBootTest
public class PersonalInformationTest {

	@InjectMocks
	PersonalInformation personalInformation = new PersonalInformation();

	@Test
	public void testNoArgConstructor() {
		assertNotNull(personalInformation);
	}

	@Test
	public void testAllArgConstructor() {
		PersonalInformation personalInformation = new PersonalInformation(1, "Nagulan", "R U", new Date(), Gender.Male,
				"6101 8953 1282", "AIUPU9900B");
		assertEquals(1, personalInformation.getId());
	}

	@Test
	public void testParamenterizedConstructor() {
		PersonalInformation personalInformation = new PersonalInformation("Nagulan", "R U", new Date(), Gender.Male,
				"6101 8953 1282", "AIUPU9900B");
		assertEquals("Nagulan", personalInformation.getFname());
	}

	@Test
	public void testId() {
		personalInformation.setId(1);
		assertEquals(1, personalInformation.getId());
	}
	
	@Test
	public void testFname() {
		personalInformation.setFname("Nagulan");
		assertEquals("Nagulan", personalInformation.getFname());
	}
	
	@Test
	public void testLname() {
		personalInformation.setLname("R U");
		assertEquals("R U", personalInformation.getLname());
	}
	
	@Test
	public void testDateOfBirth() {
		personalInformation.setDateOfBirth(new Date());;
		assertEquals(new Date(), personalInformation.getDateOfBirth());
	}
	
	@Test
	public void testGender() {
		personalInformation.setGender(Gender.Male);
		assertEquals(Gender.Male, personalInformation.getGender());
	}
	
	@Test
	public void testAadhaarCard() {
		personalInformation.setAadhaarCard("6101 8953 1282");
		assertEquals("6101 8953 1282", personalInformation.getAadhaarCard());
	}
	
	@Test
	public void testPanCard() {
		personalInformation.setPanCard("AIUPU9900B");
		assertEquals("AIUPU9900B", personalInformation.getPanCard());
	}
}

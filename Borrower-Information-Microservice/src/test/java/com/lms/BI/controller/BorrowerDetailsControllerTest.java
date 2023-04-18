package com.lms.BI.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.lms.BI.Controller.BorrowerDetailsController;
import com.lms.BI.Model.Address;
import com.lms.BI.Model.BorrowerDetails;
import com.lms.BI.Model.Gender;
import com.lms.BI.Model.PersonalInformation;
import com.lms.BI.Model.Role;
import com.lms.BI.Pojo.LoginCredentails;
import com.lms.BI.Service.BorrowerDetailsService;
import com.lms.BI.Service.TokenService;

@ContextConfiguration
@SpringBootTest
public class BorrowerDetailsControllerTest {

	@Mock
	BorrowerDetailsService borrowerDetailsService;

	@Mock
	TokenService tokenService;

	@InjectMocks
	BorrowerDetailsController borrowerDetailsController;

	@Test
	public void testGetById() {
		BorrowerDetails borrowerDetails = new BorrowerDetails(1,
				new PersonalInformation(1, "Nagulan", "R U", new Date(), Gender.Male, "6101 8953 1282", "AIUPU9900B"),
				"Nagulan R U", "1234", "8870323658", "runagulan88@gmail.com",
				new Address(1, "63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001"),
				new HashSet<Role>(), true);
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(borrowerDetailsService.getById(1)).thenReturn(borrowerDetails);
		assertEquals(new ResponseEntity<>(borrowerDetails, HttpStatus.OK),
				borrowerDetailsController.getById("Token", 1));
	}

	@Test
	public void testGetByIdNotFound() {
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(borrowerDetailsService.getById(1))
				.thenThrow(new IllegalArgumentException("No Details is found for this Id"));
		assertEquals(new ResponseEntity<>("No Details is found for this Id", HttpStatus.NOT_FOUND),
				borrowerDetailsController.getById("Token", 1));
	}

	@Test
	public void testGetByIdTokenNotValid() {
		when(tokenService.checkValidation("Token")).thenReturn(false);
		assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), borrowerDetailsController.getById("Token", 1));
	}

	@Test
	public void testGetByUname() {
		BorrowerDetails borrowerDetails = new BorrowerDetails(1,
				new PersonalInformation(1, "Nagulan", "R U", new Date(), Gender.Male, "6101 8953 1282", "AIUPU9900B"),
				"Nagulan R U", "1234", "8870323658", "runagulan88@gmail.com",
				new Address(1, "63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001"),
				new HashSet<Role>(), true);
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(borrowerDetailsService.getByUname("Nagulan R U")).thenReturn(borrowerDetails);
		assertEquals(new ResponseEntity<>(borrowerDetails, HttpStatus.OK),
				borrowerDetailsController.getByUname("Token", "Nagulan R U"));
	}

	@Test
	public void testGetByUnameNotFound() {
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(borrowerDetailsService.getByUname("Nagulan R U"))
				.thenThrow(new IllegalArgumentException("No Details is found for this User Name"));
		assertEquals(new ResponseEntity<>("No Details is found for this User Name", HttpStatus.NOT_FOUND),
				borrowerDetailsController.getByUname("Token", "Nagulan R U"));
	}

	@Test
	public void testGetByUnameTokenNotValid() {
		when(tokenService.checkValidation("Token")).thenReturn(false);
		assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN),
				borrowerDetailsController.getByUname("Token", "Nagulan R U"));
	}

	@Test
	public void testGetBAllUsers() {
		List<BorrowerDetails> borrowerDetails = new ArrayList<>();
		borrowerDetails.add(new BorrowerDetails(1,
				new PersonalInformation(1, "Nagulan", "R U", new Date(), Gender.Male, "6101 8953 1282", "AIUPU9900B"),
				"Nagulan R U", "1234", "8870323658", "runagulan88@gmail.com",
				new Address(1, "63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001"),
				new HashSet<Role>(), true));
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(borrowerDetailsService.getAllDetails()).thenReturn(borrowerDetails);
		assertEquals(new ResponseEntity<>(borrowerDetails, HttpStatus.OK),
				borrowerDetailsController.getAllUsers("Token"));
	}

	@Test
	public void testGetAllUsersNotFound() {
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(borrowerDetailsService.getAllDetails()).thenThrow(new NullPointerException("No Details Present"));
		assertEquals(new ResponseEntity<>("No Details Present", HttpStatus.NOT_FOUND),
				borrowerDetailsController.getAllUsers("Token"));
	}

	@Test
	public void testGetAllUsersTokenNotValid() {
		when(tokenService.checkValidation("Token")).thenReturn(false);
		assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), borrowerDetailsController.getAllUsers("Token"));
	}

	@Test
	public void testCheckingUniqueUsername() {
		when(borrowerDetailsService.checkUname("Nagulan R U")).thenReturn(true);
		assertEquals(new ResponseEntity<>(true, HttpStatus.OK),
				borrowerDetailsController.checkingUniqueUsername("Nagulan R U"));
	}

	@Test
	public void testCheckingUniqueEmailId() {
		when(borrowerDetailsService.checkEmailId("runagulan88@gmail.com")).thenReturn(true);
		assertEquals(new ResponseEntity<>(true, HttpStatus.OK),
				borrowerDetailsController.checkingUniqueEmailId("runagulan88@gmail.com"));
	}

	@Test
	public void testSaveEntity() {
		BorrowerDetails borrowerDetails = new BorrowerDetails(1,
				new PersonalInformation(1, "Nagulan", "R U", new Date(), Gender.Male, "6101 8953 1282", "AIUPU9900B"),
				"Nagulan R U", "1234", "8870323658", "runagulan88@gmail.com",
				new Address(1, "63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001"),
				new HashSet<Role>(), true);
		when(borrowerDetailsService.saveBorrowerDetail(borrowerDetails)).thenReturn(borrowerDetails);
		assertEquals(new ResponseEntity<>(borrowerDetails, HttpStatus.CREATED),
				borrowerDetailsController.saveEntity(borrowerDetails));
	}

	@Test
	public void testLogin() {
		Set<Role> roles = new HashSet<>();
		roles.add(new Role("ROLE_USER"));
		roles.add(new Role("ROLE_ADMIN"));
		BorrowerDetails borrowerDetails = new BorrowerDetails(1,
				new PersonalInformation(1, "Nagulan", "R U", new Date(), Gender.Male, "6101 8953 1282", "AIUPU9900B"),
				"Nagulan R U", "1234", "8870323658", "runagulan88@gmail.com",
				new Address(1, "63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001"), roles,
				true);
		when(borrowerDetailsService.getByUname("Nagulan R U")).thenReturn(borrowerDetails);
		List<String> role = new ArrayList<>();
		role.add("ROLE_ADMIN");
		role.add("ROLE_USER");
		assertEquals(new ResponseEntity<>(new LoginCredentails("Nagulan R U", "1234", role), HttpStatus.OK),
				borrowerDetailsController.login("Nagulan R U"));
	}

	@Test
	public void testLoginNotFound() {
		when(borrowerDetailsService.getByUname("Nagulan R U"))
				.thenThrow(new IllegalArgumentException("No Details is found for this User Name"));
		assertEquals(new ResponseEntity<>(new LoginCredentails("No Details is found for this User Name", null, null),
				HttpStatus.OK), borrowerDetailsController.login("Nagulan R U"));
	}

	@Test
	public void testUpdateEntity() {
		BorrowerDetails borrowerDetails = new BorrowerDetails(1,
				new PersonalInformation(1, "Nagulan", "R U", new Date(), Gender.Male, "6101 8953 1282", "AIUPU9900B"),
				"Nagulan R U", "1234", "8870323658", "runagulan88@gmail.com",
				new Address(1, "63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001"), null,
				true);
		when(tokenService.checkValidation("Token")).thenReturn(true);
		when(borrowerDetailsService.updateBorrowerDetail(borrowerDetails)).thenReturn(borrowerDetails);
		assertEquals(new ResponseEntity<>(borrowerDetails, HttpStatus.OK),
				borrowerDetailsController.updateEntity("Token", borrowerDetails));
	}

	@Test
	public void testUpdateEntityTokenNotValid() {
		BorrowerDetails borrowerDetails = new BorrowerDetails(1,
				new PersonalInformation(1, "Nagulan", "R U", new Date(), Gender.Male, "6101 8953 1282", "AIUPU9900B"),
				"Nagulan R U", "1234", "8870323658", "runagulan88@gmail.com",
				new Address(1, "63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001"), null,
				true);
		when(tokenService.checkValidation("Token")).thenReturn(false);
		assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN),
				borrowerDetailsController.updateEntity("Token", borrowerDetails));
	}
}

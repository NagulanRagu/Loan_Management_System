package com.lms.BI.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.lms.BI.Model.Address;
import com.lms.BI.Model.BorrowerDetails;
import com.lms.BI.Model.ConfirmationToken;
import com.lms.BI.Model.Gender;
import com.lms.BI.Model.PersonalInformation;
import com.lms.BI.Model.Role;
import com.lms.BI.Repository.BorrowerDetailsRepository;
import com.lms.BI.Service.BorrowerDetailsService;
import com.lms.BI.Service.ConfirmationTokenService;

@ContextConfiguration
@SpringBootTest
public class BorrowerDetailsServiceTest {

	@Mock
	BorrowerDetailsRepository borrowerDetailsRepository;

	@Mock
	ConfirmationTokenService confirmationTokenService;

	@InjectMocks
	BorrowerDetailsService borrowerDetailsService;

	@Test
	public void testGetAllDetails() {
		List<BorrowerDetails> borrowerDetails = new ArrayList<>();
		borrowerDetails.add(new BorrowerDetails(1,
				new PersonalInformation(1, "Nagulan", "R U", new Date(), Gender.Male, "6101 8953 1282", "AIUPU9900B"),
				"Nagulan R U", "1234", "8870323658", "runagulan88@gmail.com",
				new Address(1, "63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001"),
				new HashSet<Role>(), true));
		when(borrowerDetailsRepository.findAll()).thenReturn(borrowerDetails);
		assertEquals(borrowerDetails, borrowerDetailsService.getAllDetails());
	}

	@Test
	public void testGetAllDetailsException() {
		when(borrowerDetailsRepository.findAll()).thenReturn(null);
		assertThrows(NullPointerException.class, () -> borrowerDetailsService.getAllDetails());
	}

	@Test
	public void testGetById() {
		BorrowerDetails borrowerDetails = new BorrowerDetails(1,
				new PersonalInformation(1, "Nagulan", "R U", new Date(), Gender.Male, "6101 8953 1282", "AIUPU9900B"),
				"Nagulan R U", "1234", "8870323658", "runagulan88@gmail.com",
				new Address(1, "63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001"),
				new HashSet<Role>(), true);
		when(borrowerDetailsRepository.findById(1)).thenReturn(borrowerDetails);
		assertEquals(borrowerDetails, borrowerDetailsService.getById(1));
	}

	@Test
	public void testGetByIdException() {
		when(borrowerDetailsRepository.findById(1)).thenReturn(null);
		assertThrows(IllegalArgumentException.class, () -> borrowerDetailsService.getById(1));
	}

	@Test
	public void testGetByUname() {
		BorrowerDetails borrowerDetails = new BorrowerDetails(1,
				new PersonalInformation(1, "Nagulan", "R U", new Date(), Gender.Male, "6101 8953 1282", "AIUPU9900B"),
				"Nagulan R U", "1234", "8870323658", "runagulan88@gmail.com",
				new Address(1, "63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001"),
				new HashSet<Role>(), true);
		when(borrowerDetailsRepository.findByUname("Nagulan R U")).thenReturn(borrowerDetails);
		assertEquals(borrowerDetails, borrowerDetailsService.getByUname("Nagulan R U"));
	}

	@Test
	public void testGetByUnameException() {
		when(borrowerDetailsRepository.findByUname("Nagulan R U")).thenReturn(null);
		assertThrows(IllegalArgumentException.class, () -> borrowerDetailsService.getByUname("Nagulan R U"));
	}

	@Test
	public void testCheckUname() {
		when(borrowerDetailsRepository.existsByUname("Nagulan R U")).thenReturn(true);
		assertEquals(true, borrowerDetailsService.checkUname("Nagulan R U"));
	}

	@Test
	public void testCheckEmailId() {
		when(borrowerDetailsRepository.existsByEmailId("runagulan88@gmail.com")).thenReturn(true);
		assertEquals(true, borrowerDetailsService.checkEmailId("runagulan88@gmail.com"));
	}

	@Test
	public void testSaveBorrowerDetails() {
		BorrowerDetails borrowerDetails = new BorrowerDetails(1,
				new PersonalInformation(1, "Nagulan", "R U", new Date(), Gender.Male, "6101 8953 1282", "AIUPU9900B"),
				"Nagulan R U", "1234", "8870323658", "runagulan88@gmail.com",
				new Address(1, "63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001"), null,
				true);
		when(borrowerDetailsRepository.save(borrowerDetails)).thenReturn(borrowerDetails);
		Set<Role> roles = new HashSet<>();
		roles.add(new Role("ROLE_USER"));
		borrowerDetails.setRoles(roles);
		when(confirmationTokenService.createConfirmationToken(borrowerDetails))
				.thenReturn(new ConfirmationToken(1, "f3465a17-4f67-4bdd-b8ad-2af452e64116", LocalDateTime.now(),
						LocalDateTime.now().plusMinutes(15), LocalDateTime.now().plusMinutes(2), borrowerDetails));
		assertEquals(borrowerDetails.getRoles(),
				borrowerDetailsService.saveBorrowerDetail(borrowerDetails).getRoles());
	}
	
	@Test
	public void testUpdateEnable() {
		BorrowerDetails borrowerDetails = new BorrowerDetails(1, null, "Nagulan R U", "1234", "8870323658",
				"runagulan88@gmail.com", null, null, false);
		when(borrowerDetailsRepository.findById(1)).thenReturn(borrowerDetails);
		assertEquals("Enabled", borrowerDetailsService.updateEnable(1));
	}

	@Test
	public void testUpdateBorrowerDetails() {
		BorrowerDetails nBorrowerDetails = new BorrowerDetails(1,
				new PersonalInformation(1, "Nagulan", "R U", new Date(), Gender.Male, "6101 8953 1282", "AIUPU9900B"),
				"Nagulan R U", "1234", "8870323658", "runagulan88@gmail.com",
				new Address(1, "63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001"), null,
				true);
		when(borrowerDetailsRepository.save(nBorrowerDetails)).thenReturn(nBorrowerDetails);
		Set<Role> roles = new HashSet<>();
		roles.add(new Role("ROLE_USER"));
		BorrowerDetails oBorrowerDetails = new BorrowerDetails(1, null, "Nagulan R U", "1234", "8870323658",
				"runagulan88@gmail.com", null, roles, true);
		nBorrowerDetails.setRoles(roles);
		when(borrowerDetailsRepository.findById(1)).thenReturn(oBorrowerDetails);
		assertEquals(nBorrowerDetails, borrowerDetailsService.updateBorrowerDetail(nBorrowerDetails));
	}
}

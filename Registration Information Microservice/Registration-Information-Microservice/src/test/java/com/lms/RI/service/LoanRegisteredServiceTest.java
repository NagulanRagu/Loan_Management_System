package com.lms.RI.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.lms.RI.Model.GuarantorInfo;
import com.lms.RI.Model.LoanRegistered;
import com.lms.RI.Repository.LoanRegisteredRepository;
import com.lms.RI.Service.LoanRegisteredService;

@SpringBootTest
public class LoanRegisteredServiceTest {

	@Mock
	LoanRegisteredRepository loanRegisteredRepository;
	
	@InjectMocks
	LoanRegisteredService loanRegisteredService;
	
	public LoanRegistered getLoanRegistered() {
		LoanRegistered loanRegistered = new LoanRegistered(1, "Nagulan R U", "Personal", "4,00,000", "4,00,000", 60,
				"11,000", new Date(), new GuarantorInfo(), "Accepted");
		return loanRegistered;
	}
	
	@Test
	public void testGetAllDetails() {
		List<LoanRegistered> loanRegistered = new ArrayList<>();
		loanRegistered.add(getLoanRegistered());
		when(loanRegisteredRepository.findAll()).thenReturn(loanRegistered);
		assertEquals(loanRegistered, loanRegisteredService.getAllDetails());
	}
	
	@Test
	public void testGetAllDetailsNullPointerException() {
		when(loanRegisteredRepository.findAll()).thenReturn(null);
		assertThrows(NullPointerException.class, ()->loanRegisteredService.getAllDetails());
	}
	
	@Test
	public void testSaveDetails() {
		LoanRegistered loanRegistered = getLoanRegistered();
		when(loanRegisteredRepository.save(getLoanRegistered())).thenReturn(loanRegistered);
		assertEquals(loanRegistered, loanRegisteredService.saveDetails(loanRegistered));
	}
	
	@Test
	public void testGetById() {
		LoanRegistered loanRegistered = getLoanRegistered();
		when(loanRegisteredRepository.findById(1)).thenReturn(loanRegistered);
		assertEquals(loanRegistered, loanRegisteredService.getById(1));
	}
	
	@Test
	public void testGetByIdIllegalArgumentException() {
		when(loanRegisteredRepository.findById(1)).thenReturn(null);
		assertThrows(IllegalArgumentException.class, ()->loanRegisteredService.getById(1));
	}
	
	@Test
	public void testGetByBorrowerName() {
		List<LoanRegistered> loanRegistered = new ArrayList<>();
		loanRegistered.add(getLoanRegistered());
		when(loanRegisteredRepository.findByBorrowerName("Nagulan R U")).thenReturn(loanRegistered);
		assertEquals(loanRegistered, loanRegisteredService.getByBorrowerName("Nagulan R U"));
	}
	
	@Test
	public void testGetByBorrowerNameNullPointerException() {
		when(loanRegisteredRepository.findByBorrowerName("Nagulan R U")).thenReturn(null);
		assertThrows(NullPointerException.class, ()->loanRegisteredService.getByBorrowerName("Nagulan R U"));
	}
}

package com.lms.BI.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.lms.BI.Model.Address;


@SpringBootTest
public class AddressTest {
	
	@InjectMocks
	Address address = new Address();
	
	@Test
	public void testNoArgConstructor() {
		assertNotNull(address);
	}

	@Test
	public void testAllArgConstrutor() {
		Address address = new Address(1, "63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001");
		assertEquals(1, address.getId());
	}

	@Test
	public void testParamenterizedConstrutor() {
		Address address = new Address("63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001");
		assertEquals("Chinnarasingam Street, Vadasery", address.getStreet());
	}

	@Test
	public void testId() {
		address.setId(1);
		assertEquals(1, address.getId());
	}

	@Test
	public void testHouseNo() {
		address.setHouseNo("63B-72B");
		assertEquals("63B-72B", address.getHouseNo());
	}

	@Test
	public void testStreet() {
		address.setStreet("Chinnarasingam Street, Vadasery");;
		assertEquals("Chinnarasingam Street, Vadasery", address.getStreet());
	}

	@Test
	public void testCity() {
		address.setCity("Nagercoil");;
		assertEquals("Nagercoil", address.getCity());
	}

	@Test
	public void testState() {
		address.setState("TamilNadu");
		assertEquals("TamilNadu", address.getState());
	}

	@Test
	public void testPincode() {
		address.setPincode("629001");
		assertEquals("629001", address.getPincode());
	}
}

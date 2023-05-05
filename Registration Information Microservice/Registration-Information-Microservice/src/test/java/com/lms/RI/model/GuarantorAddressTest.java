package com.lms.RI.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.lms.RI.Model.GuarantorAddress;

public class GuarantorAddressTest {

	@InjectMocks
	GuarantorAddress guarantorAddress = new GuarantorAddress();
	
	@Test
	public void testNoArgConstructor() {
		assertNotNull(guarantorAddress);
	}

	@Test
	public void testAllArgConstrutor() {
		GuarantorAddress address = new GuarantorAddress(1, "63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001");
		assertEquals(1, address.getId());
	}

	@Test
	public void testParamenterizedConstrutor() {
		GuarantorAddress address = new GuarantorAddress("63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001");
		assertEquals("Chinnarasingam Street, Vadasery", address.getStreet());
	}

	@Test
	public void testId() {
		guarantorAddress.setId(1);
		assertEquals(1, guarantorAddress.getId());
	}

	@Test
	public void testHouseNo() {
		guarantorAddress.setHouseNo("63B-72B");
		assertEquals("63B-72B", guarantorAddress.getHouseNo());
	}

	@Test
	public void testStreet() {
		guarantorAddress.setStreet("Chinnarasingam Street, Vadasery");;
		assertEquals("Chinnarasingam Street, Vadasery", guarantorAddress.getStreet());
	}

	@Test
	public void testCity() {
		guarantorAddress.setCity("Nagercoil");;
		assertEquals("Nagercoil", guarantorAddress.getCity());
	}

	@Test
	public void testState() {
		guarantorAddress.setState("TamilNadu");
		assertEquals("TamilNadu", guarantorAddress.getState());
	}

	@Test
	public void testPincode() {
		guarantorAddress.setPincode("629001");
		assertEquals("629001", guarantorAddress.getPincode());
	}
}

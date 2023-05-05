package com.lms.RI.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.lms.RI.Model.GuarantorAddress;
import com.lms.RI.Model.GuarantorInfo;

@SpringBootTest
public class GuarantorInfoTest {

	@InjectMocks
	GuarantorInfo guarantorInfo = new GuarantorInfo();

	@Test
	public void testNoArgsConstructor() {
		assertNotNull(guarantorInfo);
	}

	@Test
	public void testAllArgsConstructor() {
		GuarantorInfo guarantorInfo = new GuarantorInfo(1, "Ravikumar K", "8870323658", "ravikumaravel00@gmail.com",
				new GuarantorAddress());
		assertEquals(1, guarantorInfo.getId());
	}

	@Test
	public void testParantizedConstructor() {
		GuarantorInfo guarantorInfo = new GuarantorInfo("Ravikumar K", "8870323658", "ravikumaravel00@gmail.com",
				new GuarantorAddress());
		assertEquals("Ravikumar K", guarantorInfo.getGuarantorName());
	}
	
	@Test
	public void testId() {
		guarantorInfo.setId(1);
		assertEquals(1, guarantorInfo.getId());
	}
	
	@Test
	public void testGuarantorName() {
		guarantorInfo.setGuarantorName("Ravikumar K");
		assertEquals("Ravikumar K", guarantorInfo.getGuarantorName());
	}
	
	@Test
	public void testGuarantorPhoneNo() {
		guarantorInfo.setGuarantorPhoneNo("8870323658");
		assertEquals("8870323658", guarantorInfo.getGuarantorPhoneNo());
	}
	
	@Test
	public void testGuarantorEmailId() {
		guarantorInfo.setGuarantorEmailId("ravikumaravel00@gmail.com");
		assertEquals("ravikumaravel00@gmail.com", guarantorInfo.getGuarantorEmailId());
	}

	@Test
	public void testGuarantorAddress() {
		guarantorInfo.setGuarantorAddress(
				new GuarantorAddress("63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001"));
		assertEquals("Nagercoil", guarantorInfo.getGuarantorAddress().getCity());
	}
}

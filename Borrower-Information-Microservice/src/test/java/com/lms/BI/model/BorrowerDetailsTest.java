package com.lms.BI.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.lms.BI.Model.BorrowerAddress;
import com.lms.BI.Model.BorrowerDetails;

@ContextConfiguration
@SpringBootTest
public class BorrowerDetailsTest {

    @Test
    public void testNoArgConstructor() {
        BorrowerDetails borrowerDetails = new BorrowerDetails();
        assertNotNull(borrowerDetails);
    }

    @Test
    public void testAllArgConstructor() {
        BorrowerDetails parameteredBorrowerDetails = new BorrowerDetails("Nagulan", "R U", "Nagulan R U", "1234",
                "8870323658", "runagulan88@gmail.com", "612480258080", "AUIPU9900B", new BorrowerAddress(),
                new HashSet<>());
        assertEquals("Nagulan R U", parameteredBorrowerDetails.getUname());
    }

    @Test
    public void testAllArgConstructorWithId() {
        BorrowerDetails parameteredBorrowerDetails = new BorrowerDetails(2, "Nagulan", "R U", "Nagulan R U", "1234",
                "8870323658", "runagulan88@gmail.com", "612480258080", "AUIPU9900B", new BorrowerAddress(),
                new HashSet<>());
        assertEquals(2, parameteredBorrowerDetails.getId());
    }

    @Test
    public void testId() {
        BorrowerDetails borrowerDetail = new BorrowerDetails();
        borrowerDetail.setId(2);
        assertEquals(2, borrowerDetail.getId());
    }

    @Test
    public void testFname() {
        BorrowerDetails borrowerDetail = new BorrowerDetails();
        borrowerDetail.setFname("Nagulan");
        assertEquals("Nagulan", borrowerDetail.getFname());
    }

    @Test
    public void testLname() {
        BorrowerDetails borrowerDetail = new BorrowerDetails();
        borrowerDetail.setLname("R U");
        assertEquals("R U", borrowerDetail.getLname());
    }

    @Test
    public void testUname() {
        BorrowerDetails borrowerDetail = new BorrowerDetails();
        borrowerDetail.setUname("Nagulan R U");
        assertEquals("Nagulan R U", borrowerDetail.getUname());
    }

    @Test
    public void testPassword() {
        BorrowerDetails borrowerDetail = new BorrowerDetails();
        borrowerDetail.setPassword("1234");
        assertEquals("1234", borrowerDetail.getPassword());
    }

    @Test
    public void testPhoneno() {
        BorrowerDetails borrowerDetail = new BorrowerDetails();
        borrowerDetail.setPhoneno("8870323658");
        assertEquals("8870323658", borrowerDetail.getPhoneno());
    }

    @Test
    public void testEmailId() {
        BorrowerDetails borrowerDetail = new BorrowerDetails();
        borrowerDetail.setEmailId("runagulan88@gmail.com");
        assertEquals("runagulan88@gmail.com", borrowerDetail.getEmailId());
    }

    @Test
    public void testAadhaarCard() {
        BorrowerDetails borrowerDetail = new BorrowerDetails();
        borrowerDetail.setAadhaarCard("612346578079");
        assertEquals("612346578079", borrowerDetail.getAadhaarCard());
    }

    @Test
    public void testPanCard() {
        BorrowerDetails borrowerDetail = new BorrowerDetails();
        borrowerDetail.setPanCard("AUIPU9900B");
        assertEquals("AUIPU9900B", borrowerDetail.getPanCard());
    }

    @Test
    public void testAddress() {
        BorrowerDetails borrowerDetail = new BorrowerDetails();
        borrowerDetail.setBorrowerAddress(
                new BorrowerAddress("63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001"));
        assertEquals(
                new BorrowerAddress("63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001"),
                borrowerDetail.getBorrowerAddress());
    }
}

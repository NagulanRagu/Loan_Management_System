package com.lms.BI.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.lms.BI.Model.BorrowerAddress;

@ContextConfiguration
@SpringBootTest
public class BorrowerAddressTest {
    
    @Test
    public void testNoArgConstructor() {
        BorrowerAddress borrowerAddress = new BorrowerAddress();
        assertNotNull(borrowerAddress);
    }

    @Test
    public void testAllArgConstructor() {
        BorrowerAddress borrowerAddress = new BorrowerAddress("63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001");
        assertEquals("Nagercoil", borrowerAddress.getCity());
    }

    @Test
    public void testAllArgConstructorWithId() {
        BorrowerAddress borrowerAddress = new BorrowerAddress(2, "63B-72B", "Chinnarasingam Street, Vadasery", "Nagercoil", "TamilNadu", "629001");
        assertEquals(2, borrowerAddress.getId());
    }

    @Test
    public void testId() {
        BorrowerAddress borrowerAddress = new BorrowerAddress();
        borrowerAddress.setId(2);
        assertEquals(2, borrowerAddress.getId());
    }

    @Test
    public void testHouseNo() {
        BorrowerAddress borrowerAddress = new BorrowerAddress();
        borrowerAddress.setHouseNo("63B-72B");
        assertEquals("63B-72B", borrowerAddress.getHouseNo());
    }

    @Test
    public void testStreet() {
        BorrowerAddress borrowerAddress = new BorrowerAddress();
        borrowerAddress.setStreet("Chinnarasingam Street, Vadasery");
        assertEquals("Chinnarasingam Street, Vadasery", borrowerAddress.getStreet());
    }

    @Test
    public void testCity() {
        BorrowerAddress borrowerAddress = new BorrowerAddress();
        borrowerAddress.setCity("Nagercoil");
        assertEquals("Nagercoil", borrowerAddress.getCity());
    }

    @Test
    public void testState() {
        BorrowerAddress borrowerAddress = new BorrowerAddress();
        borrowerAddress.setState("TamilNadu");
        assertEquals("TamilNadu", borrowerAddress.getState());
    }

    @Test
    public void testPincode() {
        BorrowerAddress borrowerAddress = new BorrowerAddress();
        borrowerAddress.setPincode("629001");
        assertEquals("629001", borrowerAddress.getPincode());
    }
}

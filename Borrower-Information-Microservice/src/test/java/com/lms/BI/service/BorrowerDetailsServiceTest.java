package com.lms.BI.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.lms.BI.Model.BorrowerAddress;
import com.lms.BI.Model.BorrowerDetails;
import com.lms.BI.Repository.BorrowerDetailsRepository;
import com.lms.BI.Service.BorrowerDetailsService;

@SpringBootTest
public class BorrowerDetailsServiceTest {

    @Mock
    BorrowerDetailsRepository borrowerDetailsRepository;

    @InjectMocks
    BorrowerDetailsService borrowerDetailsService;

    @Test
    public void testGetAllDetails() throws NullPointerException {
        List<BorrowerDetails> borrowerDetails = new ArrayList<>();
        borrowerDetails.add(new BorrowerDetails("Nagulan", "R U", "Nagulan R U", "1234",
        "8870323658", "runagulan88@gmail.com", "612480258080", "AUIPU9900B", new BorrowerAddress(),
        new HashSet<>()));
        when(borrowerDetailsRepository.findAll()).thenReturn(borrowerDetails);
        assertEquals(borrowerDetails, borrowerDetailsService.getAllDetails());
    }

    @Test
    public void testGetById() throws NullPointerException {
        BorrowerDetails borrowerDetails = new BorrowerDetails(1,"Nagulan", "R U", "Nagulan R U", "1234",
        "8870323658", "runagulan88@gmail.com", "612480258080", "AUIPU9900B", new BorrowerAddress(),
        new HashSet<>());
        when(borrowerDetailsRepository.findById(1)).thenReturn(borrowerDetails);
        assertEquals(borrowerDetails, borrowerDetailsService.getById(1));
    }

    @Test
    public void testGetByUname() throws NullPointerException {
        BorrowerDetails borrowerDetails = new BorrowerDetails("Nagulan", "R U", "Nagulan R U", "1234",
        "8870323658", "runagulan88@gmail.com", "612480258080", "AUIPU9900B", new BorrowerAddress(),
        new HashSet<>());
        when(borrowerDetailsRepository.findByUname("Nagulan R U")).thenReturn(borrowerDetails);
        assertEquals(borrowerDetails, borrowerDetailsService.getByUname("Nagulan R U"));
    }

    @Test
    public void testSaveBorrowerDetail(){
        BorrowerDetails borrowerDetails = new BorrowerDetails("Nagulan", "R U", "Nagulan R U", "1234",
        "8870323658", "runagulan88@gmail.com", "612480258080", "AUIPU9900B", new BorrowerAddress(),
        new HashSet<>());
        when(borrowerDetailsRepository.save(borrowerDetails)).thenReturn(borrowerDetails);
        assertEquals(borrowerDetails, borrowerDetailsService.saveBorrowerDetail(borrowerDetails));
    }
}

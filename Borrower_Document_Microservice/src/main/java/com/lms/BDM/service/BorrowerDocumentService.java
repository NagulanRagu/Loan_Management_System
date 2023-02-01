package com.lms.BDM.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lms.BDM.model.BorrowerDocument;
import com.lms.BDM.repository.BorrowerDocumentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BorrowerDocumentService {

    @Autowired
    BorrowerDocumentRepository borrowerDocumentRepository;

    public BorrowerDocument saveBorrowerDocuments(MultipartFile document, String borrowerName, String fileDetail)
            throws IOException {

        log.info("Adding new Borrower Document of {} for Borrower Name: {}", fileDetail, borrowerName);
        String fileName = StringUtils.cleanPath(document.getOriginalFilename());
        BorrowerDocument borrowerDocuments = new BorrowerDocument(borrowerName, fileDetail, fileName,
                document.getContentType(), document.getBytes());
        return borrowerDocumentRepository.save(borrowerDocuments);
    }

    public List<BorrowerDocument> getAllDocuments() throws NullPointerException {

        log.info("Fetching all details from Database");
        if (borrowerDocumentRepository.findAll().isEmpty()) {
            log.error("No Details is Found.");
            throw new NullPointerException("No Details is Found.");
        } else {
            log.info("Details are fetched from database.");
            return borrowerDocumentRepository.findAll();
        }
    }

    public BorrowerDocument getById(int id) throws IllegalArgumentException {

        log.info("Fetching detail for Id: {}", id);
        if (borrowerDocumentRepository.findById(id) == null) {
            log.error("No detail is found for Id: {}", id);
            throw new IllegalArgumentException("No detail is Found");
        } else {
            log.info("Detail are fetched for Id: {}", id);
            return borrowerDocumentRepository.findById(id);
        }
    }

    public List<BorrowerDocument> getByBorrowerName(String borrowerName) throws IllegalArgumentException {

        log.info("Fetching details for the Borrower Name: {}", borrowerName);
        if (borrowerDocumentRepository.findByBorrowerName(borrowerName).isEmpty()) {
            log.error("No Detail is Found for Borrower Name: {}", borrowerName);
            throw new IllegalArgumentException("No Detail is For this Borrower Name.");
        } else {
            log.info("All Details are fetched for Borrower Name: {}", borrowerName);
            return borrowerDocumentRepository.findByBorrowerName(borrowerName);
        }
    }

    public void deleteBorrowerDocument(int id) {

        log.info("Deleting the Document for the id: {}", id);
        borrowerDocumentRepository.deleteById(id);
    }
}

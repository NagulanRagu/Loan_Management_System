package com.lms.BDM.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.BDM.model.BorrowerDocument;

public interface BorrowerDocumentRepository extends JpaRepository<BorrowerDocument, Integer> {
    
    BorrowerDocument findById(int id);

    List<BorrowerDocument> findByBorrowerName(String borrowerName);
}

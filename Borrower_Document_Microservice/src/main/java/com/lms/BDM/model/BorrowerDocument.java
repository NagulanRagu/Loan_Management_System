package com.lms.BDM.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BorrowerDocument {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "borrower_name")
    private String borrowerName;

    @Column(name = "file_detail")
    private String fileDetail;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] file;

    public BorrowerDocument(String borrowerName, String fileDetail, String fileName, String fileType, byte[] file) {
        this.borrowerName = borrowerName;
        this.fileDetail = fileDetail;
        this.fileName = fileName;
        this.fileType = fileType;
        this.file = file;
    }

}

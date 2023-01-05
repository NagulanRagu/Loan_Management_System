package com.lms.BI.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BorrowerDetails")
public class BorrowerDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BorrowerId")
    private int id;

    @Column(name = "Firstname")
    private String fname;

    @Column(name = "Lastname")
    private String lname;

    @Column(name = "Username", unique = true)
    private String uname;

    @Column(name = "Password")
    private String password;

    @Column(name = "PhoneNumber")
    private String phoneno;

    @Column(name = "EmailId", unique = true)
    private String emailId;

    @Column(name = "Aadhaar Card")
    private String aadhaarCard;

    @Column(name = "Pan Card")
    private String panCard;

    @OneToOne(fetch = FetchType.LAZY,
              cascade = CascadeType.ALL,
              mappedBy = "borrower")
    private BorrowerAddress borrowerAddress;

    @ElementCollection
    @Column(name = "Roles")
    private List<String> roles = new ArrayList<>();

    public BorrowerDetails(String fname, String lname, String uname, String password, String phoneno, String emailId,
            String aadhaarCard, String panCard, BorrowerAddress borrowerAddress) {
        this.fname = fname;
        this.lname = lname;
        this.uname = uname;
        this.password = password;
        this.phoneno = phoneno;
        this.emailId = emailId;
        this.aadhaarCard = aadhaarCard;
        this.panCard = panCard;
        this.borrowerAddress = borrowerAddress;
    }
}

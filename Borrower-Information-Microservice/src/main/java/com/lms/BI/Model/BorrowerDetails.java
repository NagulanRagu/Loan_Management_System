package com.lms.BI.Model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "borrower_details")
public class BorrowerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstname")
    private String fname;

    @Column(name = "lastname")
    private String lname;

    @Column(name = "username", unique = true)
    private String uname;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneno;

    @Column(name = "email_id", unique = true)
    private String emailId;

    @Column(name = "aadhaar_Card")
    private String aadhaarCard;

    @Column(name = "pan_Card")
    private String panCard;

    @OneToOne(cascade = CascadeType.ALL)
    private BorrowerAddress borrowerAddress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "borrower_id", referencedColumnName = "id")
    private Set<Role> roles;

    public BorrowerDetails(String fname, String lname, String uname, String password, String phoneno, String emailId,
            String aadhaarCard, String panCard, BorrowerAddress borrowerAddress, Set<Role> roles) {
        this.fname = fname;
        this.lname = lname;
        this.uname = uname;
        this.password = password;
        this.phoneno = phoneno;
        this.emailId = emailId;
        this.aadhaarCard = aadhaarCard;
        this.panCard = panCard;
        this.borrowerAddress = borrowerAddress;
        this.roles = roles;
    }

}

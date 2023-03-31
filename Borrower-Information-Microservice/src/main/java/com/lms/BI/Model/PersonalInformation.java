package com.lms.BI.Model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PersonalInformation {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "First Name")
    private String fname;

    @Column(name = "Last Name")
    private String lname;
    
    @Column(name = "Date of Birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Gender")
    private Gender gender;
    
    @Column(name = "Aadhaar Card Number")
    private String aadhaarCard;

    @Column(name = "Pan Card Number")
    private String panCard;

	public PersonalInformation(String fname, String lname, Date dateOfBirth, Gender gender, String aadhaarCard,
			String panCard) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.aadhaarCard = aadhaarCard;
		this.panCard = panCard;
	}
}

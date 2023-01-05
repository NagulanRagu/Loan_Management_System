package com.lms.RI.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GuarantorAddress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Address Id")
    private int id;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "guarantor_id")
    private GuarantorInfo info;

    @Column(name = "House Number")
    private String houseNo;

    @Column(name = "Street")
    private String street;

    @Column(name = "City")
    private String city;

    @Column(name = "State")
    private String state;

    @Column(name = "Pincode")
    private String pincode;
}

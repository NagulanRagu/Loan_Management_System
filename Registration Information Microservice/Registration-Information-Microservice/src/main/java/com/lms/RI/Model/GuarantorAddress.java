package com.lms.RI.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guarantor_address")
public class GuarantorAddress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public GuarantorAddress(String houseNo, String street, String city, String state, String pincode) {
        this.houseNo = houseNo;
        this.street = street;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }
}

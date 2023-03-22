package com.lms.RI.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "guarantor_info")
public class GuarantorInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Guarantor Name")
    private String guarantorName;

    @Column(name = "Guarantor Phone Number")
    private String guarantorPhoneNo;

    @Column(name = "Guarantor Email Id")
    private String guarantorEmailId;

    @OneToOne(cascade = CascadeType.ALL)
    private GuarantorAddress guarantorAddress;

    public GuarantorInfo(String guarantorName, String guarantorPhoneNo, String guarantorEmailId,
        GuarantorAddress guarantorAddress) {
        this.guarantorName = guarantorName;
        this.guarantorPhoneNo = guarantorPhoneNo;
        this.guarantorEmailId = guarantorEmailId;
        this.guarantorAddress = guarantorAddress;
    }
}

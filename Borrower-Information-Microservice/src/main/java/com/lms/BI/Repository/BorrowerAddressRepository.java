package com.lms.BI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.BI.Model.BorrowerAddress;

public interface BorrowerAddressRepository extends JpaRepository<BorrowerAddress, Long> {
    
}

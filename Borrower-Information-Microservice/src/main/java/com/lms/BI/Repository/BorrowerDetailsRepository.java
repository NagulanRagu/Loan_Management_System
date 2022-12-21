package com.lms.BI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.BI.Model.BorrowerDetails;

@Repository
public interface BorrowerDetailsRepository extends JpaRepository<BorrowerDetails, Integer> {

    BorrowerDetails findById(int id);  
    
    BorrowerDetails findByUname(String uname);
}

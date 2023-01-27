package com.lms.BI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.BI.Model.BorrowerDetails;

public interface BorrowerDetailsRepository extends JpaRepository<BorrowerDetails, Integer> {

    BorrowerDetails findById(int id);

    BorrowerDetails findByUname(String uname);

    boolean existsByUname(String uname);

    boolean existsByEmailId(String emailId);
}

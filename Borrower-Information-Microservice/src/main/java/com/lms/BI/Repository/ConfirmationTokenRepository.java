package com.lms.BI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.BI.Model.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {

	ConfirmationToken findByToken(String token);
}

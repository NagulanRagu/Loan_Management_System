package com.lms.BI.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.BI.Model.BorrowerDetails;
import com.lms.BI.Model.ConfirmationToken;
import com.lms.BI.Repository.ConfirmationTokenRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConfirmationTokenService {

	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	BorrowerDetailsService borrowerDetailsService;

	public ConfirmationToken createConfirmationToken(BorrowerDetails borrowerDetails) {
		log.info("Confirmation token is creating to the user");
		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15), borrowerDetails);
		return saveConfirmationToken(confirmationToken);
	}

	public ConfirmationToken saveConfirmationToken(ConfirmationToken confirmationToken) {
		log.info("Saving the ConfirmationToken : {}", confirmationToken);
		return confirmationTokenRepository.save(confirmationToken);
	}

	public String confirmToken(String token) throws IllegalStateException {
		ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);
		if (confirmationToken == null) {
			throw new IllegalStateException("Token is not found");
		} else {
			if (confirmationToken.getConfirmedAt() != null) {
				throw new IllegalStateException("Token is already Confirmed");
			} else {
				if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
					throw new IllegalStateException("Token is Expired");
				} else {
					confirmationToken.setConfirmedAt(LocalDateTime.now());
					borrowerDetailsService.updateEnable(confirmationToken.getBorrowerDetails().getId());
					confirmationTokenRepository.save(confirmationToken);
					return "Confirmed";
				}
			}
		}
	}
}

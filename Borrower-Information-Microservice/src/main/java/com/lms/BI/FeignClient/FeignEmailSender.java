package com.lms.BI.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lms.BI.Pojo.EmailSender;

@FeignClient(url = "http://localhost:8092/", name = "confirmationToken")
public interface FeignEmailSender {

	@PostMapping("send/confirmationMail")
	public ResponseEntity<?> sendMail(@RequestBody EmailSender emailSender);
}

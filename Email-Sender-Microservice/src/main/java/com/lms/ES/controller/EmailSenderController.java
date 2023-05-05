package com.lms.ES.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.ES.pojo.EmailSender;
import com.lms.ES.service.EmailSenderService;

import jakarta.mail.MessagingException;

@RestController
public class EmailSenderController {

	@Autowired
	EmailSenderService emailSenderService;
	
	@PostMapping("send/confirmationMail")
	public ResponseEntity<?> sendMail(@RequestBody EmailSender emailSender) {
		try {
			return new ResponseEntity<>(emailSenderService.sendEmail(emailSender), HttpStatus.OK);
		}catch(MessagingException e) {
			return new ResponseEntity<>("Error is Sending the Mail", HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(MailException e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

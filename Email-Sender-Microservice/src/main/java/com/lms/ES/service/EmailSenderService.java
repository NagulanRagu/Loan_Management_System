package com.lms.ES.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.lms.ES.model.EmailTemplate;
import com.lms.ES.pojo.EmailSender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailSenderService {
	
	@Autowired
	EmailTemplateService emailTemplateService;
	
	@Autowired
	JavaMailSender javaMailSender;

	public String sendEmail(EmailSender emailSender) throws MessagingException {
		EmailTemplate emailTemplate = emailTemplateService.getByTemplateName(emailSender.getTemplateName());
		log.info("Mime Message is creating");
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setTo(emailSender.getSendTo());
		mimeMessageHelper.setFrom(new InternetAddress("runagulan88@gmail.com", false));
		mimeMessageHelper.setSubject(emailTemplate.getSubject());
		mimeMessageHelper.setText(emailTemplateService.getBody(emailTemplate.getBody(), emailSender.getVariables()), "text/html; charset=utf-8");
		javaMailSender.send(mimeMessage);
		return "Mail send successfull";
	}
}

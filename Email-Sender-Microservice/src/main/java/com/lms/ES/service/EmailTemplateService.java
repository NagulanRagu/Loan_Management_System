package com.lms.ES.service;

import java.util.Map;

import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.ES.model.EmailTemplate;
import com.lms.ES.repository.EmailTemplateRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailTemplateService {

	@Autowired
	EmailTemplateRepository emailTemplateRepository;
	
	public EmailTemplate saveTemplate(EmailTemplate emailTemplate) {
		log.info("Going to add Email Template to the Database: {}", emailTemplate);
		return emailTemplateRepository.save(emailTemplate);
	}
	
	public EmailTemplate getByTemplateName(String templateName) throws IllegalArgumentException {
		log.info("Fetching Template details from Database for Template Name: {}", templateName);
		EmailTemplate emailTemplate = emailTemplateRepository.findByTemplateName(templateName);
		if(emailTemplate == null) {
			log.error("No Template details present for Template Name: {}", templateName);
			throw new IllegalArgumentException("No Template details present for Template Name: " + templateName);
		}else {
			log.info("Template Detail is fetched from the database: {}", emailTemplate);
			return emailTemplate;
		}
	}
	
	public String getBody(String body, Map<String, String> variables) {
		StringSubstitutor substitute = new StringSubstitutor(variables);
		return substitute.replace(body);
	}
}

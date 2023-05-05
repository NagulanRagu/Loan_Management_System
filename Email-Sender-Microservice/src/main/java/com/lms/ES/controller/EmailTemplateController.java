package com.lms.ES.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.ES.model.EmailTemplate;
import com.lms.ES.service.EmailTemplateService;

@RestController
public class EmailTemplateController {

	@Autowired
	EmailTemplateService emailTemplateService;
	
	@PostMapping("template/save")
	public ResponseEntity<?> saveTemplate(@RequestBody EmailTemplate emailTemplate) {
		return new ResponseEntity<>(emailTemplateService.saveTemplate(emailTemplate), HttpStatus.CREATED);
	}
	
	@GetMapping("template/{templateName}")
	public ResponseEntity<?> getByTemplateName(@PathVariable String templateName) {
		try {
			return new ResponseEntity<>(emailTemplateService.getByTemplateName(templateName), HttpStatus.FOUND);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NO_CONTENT);
		}
	}
}
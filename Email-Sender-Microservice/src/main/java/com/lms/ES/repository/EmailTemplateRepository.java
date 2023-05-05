package com.lms.ES.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.ES.model.EmailTemplate;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Integer>  {

	EmailTemplate findByTemplateName(String templateName);
}

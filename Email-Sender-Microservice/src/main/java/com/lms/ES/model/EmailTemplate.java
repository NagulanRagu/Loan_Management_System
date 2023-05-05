package com.lms.ES.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmailTemplate {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String templateName;
	private String subject;
	private String body;
	
	public EmailTemplate(String templateName, String subject, String body) {
		super();
		this.templateName = templateName;
		this.subject = subject;
		this.body = body;
	}
}

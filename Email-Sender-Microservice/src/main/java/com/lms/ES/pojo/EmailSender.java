package com.lms.ES.pojo;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailSender {

	private String sendTo;
	private String templateName;
	private Map<String, String> variables;
}

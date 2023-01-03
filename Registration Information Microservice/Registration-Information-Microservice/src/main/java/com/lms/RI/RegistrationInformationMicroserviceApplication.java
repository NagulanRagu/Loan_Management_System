package com.lms.RI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RegistrationInformationMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationInformationMicroserviceApplication.class, args);
	}

}

package com.lms.LI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LoanInformationMicroserviceApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(LoanInformationMicroserviceApplication.class, args);
	}

}

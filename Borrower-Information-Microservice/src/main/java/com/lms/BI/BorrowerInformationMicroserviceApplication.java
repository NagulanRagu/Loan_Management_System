package com.lms.BI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BorrowerInformationMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BorrowerInformationMicroserviceApplication.class, args);
	}

}

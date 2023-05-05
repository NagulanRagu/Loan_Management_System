package com.lms.RI;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegistrationInformationMicroserviceApplicationTests {

	@Mock
	RegistrationInformationMicroserviceApplication registrationInformationMicroserviceApplication;
	
	@Test
	void contextLoads() {
		assertNotNull(registrationInformationMicroserviceApplication);
	}

}

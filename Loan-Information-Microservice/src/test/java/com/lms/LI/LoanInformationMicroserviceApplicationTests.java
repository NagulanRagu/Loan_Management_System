package com.lms.LI;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoanInformationMicroserviceApplicationTests {

	@Mock
	LoanInformationMicroserviceApplication loanInformationMicroserviceApplication;
	
	@Test
	void contextLoads() {
		assertNotNull(loanInformationMicroserviceApplication);
	}

}

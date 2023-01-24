package com.lms.BI;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BorrowerInformationMicroserviceApplicationTests {

	@Mock
	BorrowerInformationMicroserviceApplication borrowerInformationMicroserviceApplication;

	@Test
	void contextLoads() {
		assertNotNull(borrowerInformationMicroserviceApplication);
	}

}

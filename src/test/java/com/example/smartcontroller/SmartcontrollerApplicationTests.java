package com.example.smartcontroller;

import com.example.smartcontroller.controller.ApplianceController;
import com.example.smartcontroller.dataObjects.CreateApplianceDto;
import com.example.smartcontroller.enums.Status;
import com.example.smartcontroller.model.Appliance;
import com.example.smartcontroller.model.Dishwasher;
import com.example.smartcontroller.repository.ApplianceRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
class SmartcontrollerApplicationTests {

	@Autowired
	private ApplianceRepo applianceRepo;
	@Autowired
	ApplianceController applianceController;

	@Test
	void contextLoads() {
	}

	@Test
	public void canSave() {

		Appliance dishwasher = new Dishwasher();
		dishwasher.setModel("ModelTest");
		dishwasher.setName("Test Washer");
		dishwasher.setStatus(Status.OFF);

		final Appliance save = applianceRepo.save(dishwasher);
		assertNotNull(save);
	}

	@Test
	public void applianceCantBeFound_gives500() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/user/0/job")).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.statusCode());
		System.out.println(HttpStatus.INTERNAL_SERVER_ERROR);
		assertTrue(response.statusCode() == HttpStatus.INTERNAL_SERVER_ERROR.value());
	}



}

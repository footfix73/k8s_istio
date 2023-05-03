package com.example.k8sistio.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Demo1Controller {
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/service-1")
	public String getValues() {
		LocalDate localDate = LocalDate.now();
		log.info("LocalDate execution: {}", localDate);
		return "Llamando al service-1 - " + localDate;
	}
	
	@GetMapping("/service-1-2")
	public String getValueFromService2() {
		String val = restTemplate.getForObject("http://demo-2-app:8080/service-2", String.class);
		return "Llamando al service-1-2 --- " + val;
	}

}

package com.example.k8sistio.controller;

import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Demo1Controller {

	private static final String HTTP_DEMO_2_APP_8080_SERVICE_2 = "http://demo-2-app:8080/service-2";

	private static Log log = LogFactory.getLog(Demo1Controller.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/service-1")
	public String getValues() {
		log.info("Ha llamado al endpoint 1 [" + LocalDate.now() + "]");
		return "Ha llamado al servicio 1 [" + LocalDate.now() + "]";
	}
	
	@GetMapping("/service-1-2")
	public String getValueFromService2() {
		log.info("Ha llamado al endpoint 1-2 que conectara con el Microservicio 2 y el endpoint 2 [" + LocalDate.now() + "]");
		String val = restTemplate.getForObject(HTTP_DEMO_2_APP_8080_SERVICE_2, String.class);
		return "Ha llamado al servicio 1-2 que ha llamadoa al servicio 2 --- " + val;
	}

}

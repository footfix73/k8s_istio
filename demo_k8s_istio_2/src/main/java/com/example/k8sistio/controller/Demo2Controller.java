package com.example.k8sistio.controller;

import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Demo2Controller {

	public static final String HTTP_DEMO_1_APP_8080_SERVICE_3 = "http://localhost:8083/service-3";
	private static final Log log = LogFactory.getLog(Demo2Controller.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping(value = "/service-2", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getValues() {
		log.info("Llamando al servicio 2");
		return "Obteniendo en la llamada del servicio 2";
	}
	
	@GetMapping(value = "/service-2-3", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getValueFromService2() {
		log.info("Llamando al servicio 2-3");
		String val = restTemplate.getForObject(HTTP_DEMO_1_APP_8080_SERVICE_3, String.class);
		return "Ha llamado al servicio 2-3 que ha llamadoa al servicio 3---" + val;
	}

}

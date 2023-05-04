package com.example.k8sistio.controller;

import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Demo3Controller {
	
	private static Log log = LogFactory.getLog(Demo3Controller.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/service-3")
	public String getValues() {
		log.info("Llamando al servicio 3");
		return "Obteniendo en la llamada del servicio 3 [" + LocalDate.now() + "]";
	}
	
	@GetMapping("/service-3-1")
	public String getValueFromService3() {
		log.info("Llamando al servicio 3-1");
		String val = restTemplate.getForObject("http://demo-1-app:8080/service-1", String.class);
		return "Ha llamado al servicio 3-1 que ha llamadoa al servicio 1 --- " + val;
	}

}

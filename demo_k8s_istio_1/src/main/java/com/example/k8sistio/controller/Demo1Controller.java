package com.example.k8sistio.controller;

import java.time.LocalDate;

import com.example.k8sistio.response.ResponseCustom;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Demo1Controller {

	public static final String HA_LLAMADO_AL_SERVICIO_1_2_3_QUE_HA_LLAMADOA_AL_SERVICIO_2 = "Ha llamado al servicio 1-2-3 que ha llamadoa al servicio 2";
	@Value("${micro.service2}")
	private String service2;
	@Value("${micro.service23}")
	private String service23;

	private static Log log = LogFactory.getLog(Demo1Controller.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/service-1")
	public String getValues() {
		log.info("Ha llamado al endpoint 1 [" + LocalDate.now() + "]");
		return "Ha llamado al servicio 1 [" + LocalDate.now() + "]";
	}
	
	@GetMapping("/service-1-2-3")
	public ResponseEntity<ResponseCustom> getValueFromService2() {
		log.info("Ha llamado al endpoint 1-2 que conectara con el Microservicio 2 y el endpoint 2 [" + LocalDate.now() + "]");
		String val = restTemplate.getForObject(service23, String.class);

		if (val == null) {
			return ResponseEntity.notFound().build();
		} else {
			String[] parts = val.split("---");
			ResponseCustom responseCustom = new ResponseCustom(HA_LLAMADO_AL_SERVICIO_1_2_3_QUE_HA_LLAMADOA_AL_SERVICIO_2,parts[1],parts[0]);

			return ResponseEntity.ok(responseCustom);
		}
	}

}

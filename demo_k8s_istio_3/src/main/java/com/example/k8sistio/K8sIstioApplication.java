package com.example.k8sistio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class K8sIstioApplication {

	public static void main(String[] args) {
		SpringApplication.run(K8sIstioApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
	  return new RestTemplate();
	}
}

package com.example.k8sistio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.k8sistio.*"})
@Slf4j
public class K8sIstioApplication {

	public static void main(String[] args) {
		log.info("Inicializamos el micros Demo1 ...");
		SpringApplication.run(K8sIstioApplication.class, args);
	}

}

package com.example.k8sistio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.k8sistio.*"})
public class K8sIstioApplication {

	public static void main(String[] args) {
		SpringApplication.run(K8sIstioApplication.class, args);
	}

}

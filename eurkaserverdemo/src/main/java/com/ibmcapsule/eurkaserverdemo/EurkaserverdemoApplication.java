package com.ibmcapsule.eurkaserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurkaserverdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurkaserverdemoApplication.class, args);
	}

}

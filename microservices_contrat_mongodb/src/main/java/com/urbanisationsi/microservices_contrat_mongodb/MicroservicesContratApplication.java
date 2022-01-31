package com.urbanisationsi.microservices_contrat_mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MicroservicesContratApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesContratApplication.class, args);
	}

}

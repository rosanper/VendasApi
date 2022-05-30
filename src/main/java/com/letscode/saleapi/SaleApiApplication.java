package com.letscode.saleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class SaleApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaleApiApplication.class, args);
	}

}

package com.letscode.saleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
//@EnableEurekaClient
public class SaleApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaleApiApplication.class, args);
	}

}

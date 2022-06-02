package com.letscode.saleapi.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfig {

    @Value("${spring.data.viacep.uri}")
    private String cepClientUri;

    @Value("${spring.data.user-api.uri}")
    private String userClientUri;

    @Value("${spring.data.product-api.uri}")
    private String productClientUri;


    @Bean
    public WebClient webClientCep(WebClient.Builder builder){
        return builder
                .baseUrl(cepClientUri)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public WebClient webClientUser(WebClient.Builder builder){
        return builder
                .baseUrl(userClientUri)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public WebClient webClientProduct(WebClient.Builder builder){
        return builder
                .baseUrl(productClientUri)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}

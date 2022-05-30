package com.letscode.saleapi.client;

import com.letscode.saleapi.dto.Cep;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CepClientService {

    private final WebClient webClientCep;

    public Mono<Cep> getCep(String cep){
        return this.webClientCep
                .method(HttpMethod.GET)
                .uri("/{cep}/json/", cep)
                .retrieve()
                .bodyToMono(Cep.class);
    }




}

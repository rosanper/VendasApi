package com.letscode.saleapi.client;

import com.letscode.saleapi.dto.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductClientService {

    private final WebClient webClientProduct;

    public Mono<Product> getProduct(String productId){
        return this.webClientProduct
                .method(HttpMethod.GET)
                .uri("/product/{id}", productId)
                .retrieve()
                .bodyToMono(Product.class);

    }
}

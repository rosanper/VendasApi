package com.letscode.saleapi.client;

import com.letscode.saleapi.dto.Product;
import com.letscode.saleapi.dto.User;
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
        //Confirmar o uri, o método e a forma de enviar a informação
        return this.webClientProduct
                .method(HttpMethod.GET)
                .uri("/product/{id}", productId)
                .retrieve()
                .bodyToMono(Product.class);

    }
}

package com.letscode.saleapi.service;

import com.letscode.saleapi.models.Cart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SaleService {
    Flux<Cart> getAll();
    Mono<Cart> createCart();
    Mono<Cart> getCart(String id);
    Mono<Void> deleteCart(String id);

}

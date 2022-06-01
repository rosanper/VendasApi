package com.letscode.saleapi.service;

import com.letscode.saleapi.models.Cart;
import com.letscode.saleapi.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SaleRepositoryService {
    private final SaleRepository saleRepository;

    public Flux<Cart> getAll() {
        return saleRepository.findAll();
    }

    public Mono<Cart> getCart(String id) {
        return saleRepository.findById(id);
    }

    public Mono<Void> deleteCart(String id) {
        return saleRepository.deleteById(id);
    }

    private Mono<Cart> saveCart(Cart cart){
        Mono<Cart> save = saleRepository.save(cart);
        return save;
    }
}

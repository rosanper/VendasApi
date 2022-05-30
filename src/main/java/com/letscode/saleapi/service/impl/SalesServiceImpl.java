package com.letscode.saleapi.service.impl;

import com.letscode.saleapi.models.Cart;
import com.letscode.saleapi.repositories.SaleRepository;
import com.letscode.saleapi.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    @Override
    public Flux<Cart> getAll() {
        return saleRepository.findAll();
    }

    @Override
    public Mono<Cart> createCart() {
        Cart cart = new Cart(UUID.randomUUID().toString());
        return saleRepository.save(cart);
    }

    @Override
    public Mono<Cart> getCart(String id) {
        return saleRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteCart(String id) {
        return saleRepository.deleteById(id);
    }
}

package com.letscode.saleapi.service;

import com.letscode.saleapi.exceptions.NotExistException;
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
        return saleRepository.findById(id).switchIfEmpty(Mono.error(new NotExistException("Não existe carrinho com esse id")));
    }

    public Mono<Void> deleteCart(String id) {
        return saleRepository.deleteById(id);
    }

    public Mono<Cart> saveCart(Cart cart){
        Mono<Cart> save = saleRepository.save(cart);
        return save;
    }
}

package com.letscode.saleapi.service;

import com.letscode.saleapi.enums.Status;
import com.letscode.saleapi.exceptions.BusinessException;
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
        return getCart(id).map(cart -> verifyDeleteConditions(cart)).flatMap(cart -> saleRepository.deleteById(id));
    }

    private Cart verifyDeleteConditions(Cart cart){
        if (cart.getStatusCart() != Status.MOUNTING) throw new BusinessException("O carrinho já foi finalizado e não pode ser deletado");
        return cart;
    }

    public Mono<Cart> saveCart(Cart cart){
        Mono<Cart> save = saleRepository.save(cart);
        return save;
    }

    public Flux<Cart> getCartUser(String id){
        return saleRepository.findByUserId(id)
                .switchIfEmpty(Mono.error(new NotExistException("Não existe carrinho com esse id de usuario")));
    }
}

package com.letscode.saleapi.service;

import com.letscode.saleapi.client.CepClientService;
import com.letscode.saleapi.dto.Cep;
import com.letscode.saleapi.dto.CepRequest;
import com.letscode.saleapi.models.Cart;
import com.letscode.saleapi.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CepService {

    private final CepClientService cepClientService;

    private final SaleRepository saleRepository;

    public Mono<Cart> addCep(CepRequest cepRequest){
        Mono<Cep> cep = cepClientService.getCep(cepRequest.getCep());
        Mono<Cart> cart = saleRepository.findById(cepRequest.getCartId());
        return Mono.zip(cart,cep).map(tuple -> this.setCep(tuple.getT1(), tuple.getT2())).flatMap(this::saveCart);
    }

    private Cart setCep(Cart cart, Cep cep){
        if (cep.getCep() == null) throw new RuntimeException("erro com cep invalido");
        cart.setCep(cep);
        return cart;
    }

    private Mono<Cart> saveCart(Cart cart){
        Mono<Cart> save = saleRepository.save(cart);
        return save;
    }





}

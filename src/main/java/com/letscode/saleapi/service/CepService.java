package com.letscode.saleapi.service;

import com.letscode.saleapi.client.CepClientService;
import com.letscode.saleapi.dto.Cep;
import com.letscode.saleapi.dto.CepRequest;
import com.letscode.saleapi.exceptions.NotExistException;
import com.letscode.saleapi.models.Cart;
import com.letscode.saleapi.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CepService {

    private final CepClientService cepClientService;

    private final SaleRepositoryService saleRepositoryService;

    public Mono<Cart> addCep(CepRequest cepRequest){
        Mono<Cep> cep = cepClientService.getCep(cepRequest.getCep());
        Mono<Cart> cart = saleRepositoryService.getCart(cepRequest.getCartId());
        return Mono.zip(cart,cep)
                .map(tuple -> this.setCep(tuple.getT1(), tuple.getT2()))
                .flatMap(saleRepositoryService::saveCart);
    }

    private Cart setCep(Cart cart, Cep cep){
        if (cep.getCep() == null) throw new NotExistException("Cep invalido");
        cart.setCep(cep);
        return cart;
    }






}

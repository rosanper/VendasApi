package com.letscode.saleapi.controller;

import com.letscode.saleapi.client.CepClientService;
import com.letscode.saleapi.dto.Cep;
import com.letscode.saleapi.dto.UserRequest;
import com.letscode.saleapi.models.Cart;
import com.letscode.saleapi.service.CreateCartService;
import com.letscode.saleapi.service.impl.SalesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SalesServiceImpl salesService;

    private final CepClientService cepClientService;

    private final CreateCartService createCartService;

    @GetMapping
    public Flux<Cart> getAll(){
        return salesService.getAll();
    }

    @PostMapping
    public Mono<Cart> createCart(@RequestBody UserRequest userRequest){

        return createCartService.execute(userRequest);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCart(@PathVariable String id){
        return salesService.deleteCart(id);
    }

    @GetMapping("/{id}")
    public Mono<Cart> getCart(@PathVariable String id){
        return salesService.getCart(id);
    }

    @GetMapping("/cep/{cep}")
    public Mono<Cep> getCep(@PathVariable String cep){
        return cepClientService.getCep(cep);
    }

}

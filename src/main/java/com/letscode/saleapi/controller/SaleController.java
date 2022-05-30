package com.letscode.saleapi.controller;

import com.letscode.saleapi.models.Cart;
import com.letscode.saleapi.repositories.SaleRepository;
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

    @GetMapping
    public Flux<Cart> getAll(){
        return salesService.getAll();
    }

    @PostMapping
    public Mono<Cart> createCart(){
        return salesService.createCart();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCart(@PathVariable String id){
        return salesService.deleteCart(id);
    }

    @GetMapping("/{id}")
    public Mono<Cart> getCart(@PathVariable String id){
        return salesService.getCart(id);
    }

}

package com.letscode.saleapi.controller;

import com.letscode.saleapi.client.CepClientService;
import com.letscode.saleapi.client.ProductClientService;
import com.letscode.saleapi.dto.*;
import com.letscode.saleapi.models.Cart;
import com.letscode.saleapi.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final CreateCartService createCartService;

    private final AddProductService addProductService;

    private final RemoveProductService removeProductService;

    private final CepService cepService;

    private final SaleRepositoryService saleRepositoryService;

    private final FinishCartService finishCartService;

    private final StatusService statusService;

    @GetMapping
    public Flux<Cart> getAll(){
        return saleRepositoryService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Cart> getCart(@PathVariable String id){
        return saleRepositoryService.getCart(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCart(@PathVariable String id){
        return saleRepositoryService.deleteCart(id);
    }

    @PostMapping
    public Mono<Cart> createCart(@RequestBody UserIdRequest userIdRequest){
        return createCartService.execute(userIdRequest);
    }

    @PutMapping("/addProduct")
    public Mono<Cart> addProduct(@RequestBody ProductRequest productRequest){
        return addProductService.execute(productRequest);
    }

    @PutMapping("/removeProduct")
    public Mono<Cart> removeProduct(@RequestBody ProductRequest productRequest){
        return removeProductService.execute(productRequest);
    }

    @PutMapping("addCep")
    public Mono<Cart> setCep(@RequestBody CepRequest cepRequest){
        return cepService.addCep(cepRequest);
    }

    @PutMapping("/finishCart")
    public Mono<Cart> finishCart(@RequestBody FinishCartRequest finishCartRequest){
        return finishCartService.execute(finishCartRequest);
    }

    @GetMapping("/userId/{id}")
    public Flux<Cart> getCartUser(@PathVariable String id){
        return saleRepositoryService.getCartUser(id);
    }

    @PutMapping("/status")
    public Mono<Cart> updateStatus(@RequestBody StatusRequest statusRequest){
        return statusService.updateStatus(statusRequest);
    }


}

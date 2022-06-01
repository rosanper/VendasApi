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

    private final CepClientService cepClientService;

    private final ProductClientService productClientService;

    private final CreateCartService createCartService;

    private final AddProductService addProductService;

    private final RemoveProductService removeProductService;

    private final CepService cepService;

    private final SaleRepositoryService saleRepositoryService;

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

    @GetMapping("/cep/{cep}")
    public Mono<Cep> getCep(@PathVariable String cep){
        return cepClientService.getCep(cep);
    }

    @GetMapping("/product")
    public Mono<Product> getProduct(@RequestBody ProductRequest productRequest){
        return productClientService.getProduct(productRequest.getProductId());
    }

    @PutMapping("/{id}")
    public Mono<Cart> addProduct(@RequestBody ProductRequest productRequest,@PathVariable String id){
        return addProductService.execute(productRequest,id);
    }

    @PutMapping("/{id}/remove")
    public Mono<Cart> removeProduct(@RequestBody ProductRequest productRequest,@PathVariable String id){
        return removeProductService.execute(productRequest,id);
    }

    @PutMapping
    public Mono<Cart> setCep(@RequestBody CepRequest cepRequest){
        return cepService.addCep(cepRequest);
    }


}

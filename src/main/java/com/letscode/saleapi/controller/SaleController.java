package com.letscode.saleapi.controller;

import com.letscode.saleapi.client.CepClientService;
import com.letscode.saleapi.client.ProductClientService;
import com.letscode.saleapi.dto.Cep;
import com.letscode.saleapi.dto.Product;
import com.letscode.saleapi.dto.ProductRequest;
import com.letscode.saleapi.dto.UserRequest;
import com.letscode.saleapi.models.Cart;
import com.letscode.saleapi.service.AddProductService;
import com.letscode.saleapi.service.CreateCartService;
import com.letscode.saleapi.service.RemoveProductService;
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

    private final ProductClientService productClientService;

    private final CreateCartService createCartService;

    private final AddProductService addProductService;

    private final RemoveProductService removeProductService;

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

}

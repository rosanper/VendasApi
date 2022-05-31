package com.letscode.saleapi.service;

import com.letscode.saleapi.client.ProductClientService;
import com.letscode.saleapi.dto.Product;
import com.letscode.saleapi.dto.ProductRequest;
import com.letscode.saleapi.models.Cart;
import com.letscode.saleapi.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddProductService {

    private final SaleRepository saleRepository;

    private final ProductClientService productClientService;

    public Mono<Cart> execute(ProductRequest productRequest, String cartId){
        Mono<Cart> cart = saleRepository.findById(cartId);
        Mono<Product> product = productClientService.getProduct(productRequest.getProductId());

        return Mono.zip(cart, product).map(t -> this.addProduct(t.getT1(), t.getT2()))
                .flatMap(this::saveCart);


        //receber o id do cart e do produto
        //buscar cart
        //buscar produto
        //atualizar carrinho
    }

    private Cart addProduct(Cart cart, Product product){
        List<Product> products = cart.getProducts();
        products.add(product);
        cart.setProducts(products);
        return cart;
    }

    private Mono<Cart> saveCart(Cart cart){
        Mono<Cart> save = saleRepository.save(cart);
        return save;
    }
}

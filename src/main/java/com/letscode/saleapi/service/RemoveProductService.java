package com.letscode.saleapi.service;

import com.letscode.saleapi.dto.Product;
import com.letscode.saleapi.dto.ProductRequest;
import com.letscode.saleapi.models.Cart;
import com.letscode.saleapi.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RemoveProductService {

    private final SaleRepository saleRepository;

    private final CalculatorPriceService calculatorPriceService;

    public Mono<Cart> execute(ProductRequest productRequest, String cartId){
        return saleRepository.findById(cartId)
                .map(t -> this.removeProduct(t,productRequest))
                .map(this::updateTotalPrice)
                .flatMap(this::saveCart);
    }

    private Cart removeProduct(Cart cart, ProductRequest productRequest){
        List<Product> products = cart.getProducts();
        List<String> productsId = products.stream().map(Product::getId).collect(Collectors.toList());

        //erro caso não tenha o produto
        if(!productsId.contains(productRequest.getProductId())) throw new RuntimeException("erro não existe produto");

        int index = productsId.indexOf(productRequest.getProductId());
        Product prod = products.get(index);
        int newQuantity = prod.getQuantity() - productRequest.getQuantity();
        if(newQuantity <= 0){
            products.remove(index);
        }else {
            prod.setQuantity(newQuantity);
            products.set(index,prod);
        }

        cart.setProducts(products);
        return cart;
    }

    private Cart updateTotalPrice(Cart cart){
        BigDecimal total = calculatorPriceService.execute(cart);
        cart.setTotalPrice(total);
        return cart;
    }

    private Mono<Cart> saveCart(Cart cart){
        Mono<Cart> save = saleRepository.save(cart);
        return save;
    }

}

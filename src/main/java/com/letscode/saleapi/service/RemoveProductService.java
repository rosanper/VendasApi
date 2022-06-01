package com.letscode.saleapi.service;

import com.letscode.saleapi.dto.Product;
import com.letscode.saleapi.dto.ProductRequest;
import com.letscode.saleapi.exceptions.NotExistException;
import com.letscode.saleapi.models.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RemoveProductService {

    private final PriceService priceService;

    private final SaleRepositoryService saleRepositoryService;

    public Mono<Cart> execute(ProductRequest productRequest){
        return saleRepositoryService.getCart(productRequest.getCartId())
                .map(t -> this.removeProduct(t,productRequest))
                .map(priceService::updateTotalPrice)
                .flatMap(saleRepositoryService::saveCart);
    }

    private Cart removeProduct(Cart cart, ProductRequest productRequest){
        List<Product> products = cart.getProducts();
        List<String> productsId = products.stream().map(Product::getId).collect(Collectors.toList());

        if(!productsId.contains(productRequest.getProductId()))
            throw new NotExistException("Não existe produto com esse id");

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

}

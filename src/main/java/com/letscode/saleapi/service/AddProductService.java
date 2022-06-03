package com.letscode.saleapi.service;

import com.letscode.saleapi.client.ProductClientService;
import com.letscode.saleapi.dto.Product;
import com.letscode.saleapi.dto.ProductRequest;
import com.letscode.saleapi.enums.Status;
import com.letscode.saleapi.exceptions.BusinessException;
import com.letscode.saleapi.models.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddProductService {

    private final ProductClientService productClientService;

    private final PriceService priceService;

    private final SaleRepositoryService saleRepositoryService;

    public Mono<Cart> execute(ProductRequest productRequest){
        Mono<Cart> cart = saleRepositoryService.getCart(productRequest.getCartId())
                .map(c -> verifyUser(c, productRequest.getUserId()));

        Mono<Product> product = productClientService.getProduct(productRequest.getProductId())
                .map(p -> this.setProductQuantity(p, productRequest.getQuantity()));

        return Mono.zip(cart, product).map(t -> this.addProduct(t.getT1(), t.getT2()))
                .map(priceService::updateTotalPrice)
                .flatMap(saleRepositoryService::saveCart);
    }

    public Cart verifyUser(Cart cart, String userId){
        if (!cart.getUserId().equalsIgnoreCase(userId)){
            throw new BusinessException("O id do usuairo passado não corresponde ao id do usuário do carrinho");
        }
        return cart;
    }

    public void validateStatusCart(Cart cart){
        if (cart.getStatusCart() != Status.MOUNTING) throw new BusinessException("Esse carrinho não está mais sendo montado");
    }

    private Cart addProduct(Cart cart, Product product){
        validateStatusCart(cart);
        List<Product> products = cart.getProducts();
        List<String> productsId = products.stream().map(Product::getId).collect(Collectors.toList());
        if (productsId.contains(product.getId())){
            int index = productsId.indexOf(product.getId());
            Product prod = products.get(index);
            int newQuantity = prod.getQuantity() + product.getQuantity();
            prod.setQuantity(newQuantity);
            products.set(index,prod);
        }else {
            products.add(product);
        }
        cart.setProducts(products);
        return cart;
    }

    private Product setProductQuantity(Product product, int quantity){
        product.setQuantity(quantity);
        return product;
    }

}

package com.letscode.saleapi.service;

import com.letscode.saleapi.client.ProductClientService;
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
public class AddProductService {

    private final SaleRepository saleRepository;

    private final ProductClientService productClientService;

    private final CalculatorPriceService calculatorPriceService;

    public Mono<Cart> execute(ProductRequest productRequest, String cartId){
        Mono<Cart> cart = saleRepository.findById(cartId);
        Mono<Product> product = productClientService.getProduct(productRequest.getProductId())
                .map(p -> this.setProductQuantity(p, productRequest.getQuantity()));

        return Mono.zip(cart, product).map(t -> this.addProduct(t.getT1(), t.getT2()))
                .map(this::updateTotalPrice)
                .flatMap(this::saveCart);


        //receber o id do cart e do produto
        //buscar cart
        //buscar produto
        //atualizar carrinho
    }

    private Cart addProduct(Cart cart, Product product){
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

    private Cart updateTotalPrice(Cart cart){
        BigDecimal total = calculatorPriceService.execute(cart);
        cart.setTotalPrice(total);
        return cart;
    }

    private Product setProductQuantity(Product product, int quantity){
        product.setQuantity(quantity);
        return product;
    }

    private Mono<Cart> saveCart(Cart cart){
        Mono<Cart> save = saleRepository.save(cart);
        return save;
    }
}

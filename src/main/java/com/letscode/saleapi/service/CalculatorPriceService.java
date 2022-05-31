package com.letscode.saleapi.service;

import com.letscode.saleapi.dto.Product;
import com.letscode.saleapi.models.Cart;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CalculatorPriceService {

    public BigDecimal execute(Cart cart){
        List<Product> products = cart.getProducts();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Product product : products) {
            totalPrice = totalPrice.add(product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())));
        }
        return totalPrice;
    }

}

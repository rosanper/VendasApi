package com.letscode.saleapi.repositories;

import com.letscode.saleapi.models.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SaleRepository extends ReactiveCrudRepository<Cart,String> {
}

package com.letscode.saleapi.repositories;

import com.letscode.saleapi.models.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface SaleRepository extends ReactiveCrudRepository<Cart,String> {
    Flux<Cart> findByUserId(String userId);
}

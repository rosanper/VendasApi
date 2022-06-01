package com.letscode.saleapi.service;

import com.letscode.saleapi.client.UserClientService;
import com.letscode.saleapi.dto.User;
import com.letscode.saleapi.dto.UserIdRequest;
import com.letscode.saleapi.models.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateCartService {

    private final UserClientService userClientService;

    private final SaleRepositoryService saleRepositoryService;

    public Mono<Cart> execute(UserIdRequest userIdRequest){
        return userClientService.getClient(userIdRequest.getUserId())
                .map(user -> this.createCart(user))
                .flatMap(cart -> saleRepositoryService.saveCart(cart));
    }

    private Cart createCart(User user){
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        return cart;
    }

}

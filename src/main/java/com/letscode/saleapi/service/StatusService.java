package com.letscode.saleapi.service;

import com.letscode.saleapi.dto.StatusRequest;
import com.letscode.saleapi.enums.Status;
import com.letscode.saleapi.exceptions.BusinessException;
import com.letscode.saleapi.models.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final SaleRepositoryService saleRepositoryService;

    public Mono<Cart> updateStatus(StatusRequest statusRequest){
        return saleRepositoryService.getCart(statusRequest.getCartId()).map(cart -> {
            if (cart.getStatusCart() != Status.IN_TRANSIT) throw new BusinessException("Esse carrinho não pode ser finalizado");
            cart.setStatusCart(statusRequest.getStatusCart());
            return cart;
        }).flatMap(saleRepositoryService::saveCart);
    }


}

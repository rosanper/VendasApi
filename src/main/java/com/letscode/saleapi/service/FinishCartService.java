package com.letscode.saleapi.service;

import com.letscode.saleapi.client.UserClientService;
import com.letscode.saleapi.dto.FinishCartRequest;
import com.letscode.saleapi.dto.User;
import com.letscode.saleapi.enums.Status;
import com.letscode.saleapi.exceptions.BusinessException;
import com.letscode.saleapi.models.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FinishCartService {

    private final SaleRepositoryService saleRepositoryService;

    private final UserClientService userClientService;

    public Mono<Cart> execute(FinishCartRequest finishCartRequest){
        Mono<Cart> cart = saleRepositoryService.getCart(finishCartRequest.getCartId())
                .map(this::setCart);

        Mono<User> user = userClientService.getClient(finishCartRequest.getUserId())
                .map(u -> verifyUserCpfAndPassword(u, finishCartRequest.getCpf(), finishCartRequest.getPassword()));

        return Mono.zip(cart,user)
                .map(tuple -> verifyUserId(tuple.getT1(), tuple.getT2()))
                .flatMap(saleRepositoryService::saveCart);

    }

    private Cart verifyUserId(Cart cart, User user){
        if(cart.getUserId() == user.getId()) throw new BusinessException("userId da requisição diferente da do carrinho");
        return cart;
    }

    private User verifyUserCpfAndPassword(User user, String cpf, String password){
        if (!user.getCpf().equalsIgnoreCase(cpf) || !user.getPassword().equalsIgnoreCase(password))
            throw new BusinessException("Erro na validação do usuario (cpf e/ou password incorretos)");
        return user;
    }

    private void verifyCep(Cart cart){
        if(cart.getCep() == null) throw new BusinessException("Cep não registrado");
    }

    private void verifyProducts(Cart cart){
        if(cart.getProducts().isEmpty()) throw new BusinessException("Carrinho vazio");
    }

    private void verifyStatusCart(Cart cart){
        if(cart.getStatusCart() != Status.MOUNTING) throw new BusinessException("Carrinho já finalizado");
    }

    private Cart updateStatusCart(Cart cart){
        cart.setStatusCart(Status.IN_TRANSIT);
        cart.setPurchaseDate(LocalDateTime.now());
        return cart;
    }

    private Cart setCart(Cart cart){
        verifyCep(cart);
        verifyProducts(cart);
        verifyStatusCart(cart);
        Cart cartModified = updateStatusCart(cart);
        return cartModified;
    }

}

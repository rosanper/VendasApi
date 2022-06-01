package com.letscode.saleapi.service;

import com.letscode.saleapi.client.UserClientService;
import com.letscode.saleapi.dto.FinishCartRequest;
import com.letscode.saleapi.dto.User;
import com.letscode.saleapi.enums.Status;
import com.letscode.saleapi.models.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FinishCartService {

    //receber o id do carrinho, cpf e senha (OK)
    // validar cpf e senha
    // verificar se cep e produtos não estao vazio (OK)
    // alterar status do carrinho para em_transito (OK)

    private final SaleRepositoryService saleRepositoryService;

    private final UserClientService userClientService;

    public Mono<Cart> execute(FinishCartRequest finishCartRequest){
        Mono<Cart> cart = saleRepositoryService.getCart(finishCartRequest.getCartId())
                .map(this::setCart)
                .flatMap(saleRepositoryService::saveCart);
        return cart;
    }

    private User verifyUserCpfAndPassword(User user, String cpf, String password){
        if (!user.getCpf().equalsIgnoreCase(cpf) || !user.getPassword().equalsIgnoreCase(password))
            throw new RuntimeException("erro verificacao usuario");
        return user;
    }

    private void verifyCep(Cart cart){
        if(cart.getCep() == null) throw new RuntimeException("erro verificacao cep");
    }

    private void verifyProducts(Cart cart){
        if(cart.getProducts().isEmpty()) throw new RuntimeException("erro verificacao carrinho");
    }

    private void verifyStatusCart(Cart cart){
        if(cart.getStatusCart() != Status.MONTANDO) throw new RuntimeException("erro verificacao status");
    }

    private Cart updateStatusCart(Cart cart){
        cart.setStatusCart(Status.EM_TRANSITO);
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

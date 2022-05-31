package com.letscode.saleapi.service;

import com.letscode.saleapi.client.UserClientService;
import com.letscode.saleapi.dto.User;
import com.letscode.saleapi.dto.UserRequest;
import com.letscode.saleapi.models.Cart;
import com.letscode.saleapi.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateCartService {

    private final SaleRepository saleRepository;

    private final UserClientService userClientService;

    public Mono<Cart> execute(UserRequest userRequest){
        return userClientService.getClient(userRequest)
                .map(user -> this.verifyUserPassword(user,userRequest))
                .map(user -> createCart(user))
                .flatMap(cart -> this.saveCart(cart));

        //receber UserRequest
        //Verificar se o cliente existe
        //Criar carrinho
        //enviar id do carrinho para usuario

    }

    private User verifyUserPassword(User user, UserRequest userRequest){
        if (!user.getPassword().equalsIgnoreCase(userRequest.getPassword()) ||
                !user.getCpf().equalsIgnoreCase(user.getCpf())) throw new RuntimeException("erro usuario");
        return user;
    }

    private Cart createCart(User user){
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        this.sendCartIdToUser(cart);
        return cart;
    }

    private Mono<Void> sendCartIdToUser(Cart cart){
        String cartId = cart.getId();
        return userClientService.updateClient(cartId);
    }

    private Mono<Cart> saveCart(Cart cart){
        Mono<Cart> save = saleRepository.save(cart);
        return save;
    }


}

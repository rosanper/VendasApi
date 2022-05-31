package com.letscode.saleapi.client;


import com.letscode.saleapi.dto.User;
import com.letscode.saleapi.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserClientService {

    private final WebClient webClientUser;

    public Mono<User> getClient(UserRequest userRequest){
        //Confirmar o uri, o método e a forma de enviar a informação
//        return this.webClientUser
//                .method(HttpMethod.GET)
//                .uri("/users")
//                .body(BodyInserters.fromValue(userRequest))
//                .retrieve()
//                .bodyToMono(User.class);

        User user = new User();
        user.setCpf(userRequest.getCpf());
        user.setPassword(userRequest.getPassword());
        user.setEmail("teste@teste.com");
        user.setName("teste");
        user.setId("teste");
        user.setTell("111111111");
        return Mono.just(user);

    }

    public Mono<Void> updateClient(String cartId){
        //Confirmar o uri, o método e a forma de enviar a informação
//        return this.webClientUser
//                .put()
//                .uri("users")
//                .body(BodyInserters.fromValue(cartId))
//                .retrieve()
//                .bodyToMono(Void.class);
        System.out.println("mensagem enviada, id: " + cartId);
        return Mono.empty();
    }
}

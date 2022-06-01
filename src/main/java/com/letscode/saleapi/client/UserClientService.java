package com.letscode.saleapi.client;


import com.letscode.saleapi.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserClientService {

    private final WebClient webClientUser;

    public Mono<User> getClient(String userId){

        return this.webClientUser
                .method(HttpMethod.GET)
                .uri("/users/{id}", userId)
                .retrieve()
                .bodyToMono(User.class);
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

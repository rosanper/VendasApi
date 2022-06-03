package com.letscode.saleapi.client;


import com.letscode.saleapi.dto.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserClientService {

    private final WebClient webClientUser;

    @CircuitBreaker(name = "user-circuitbreak")
    public Mono<User> getClient(String userId){

        return this.webClientUser
                .method(HttpMethod.GET)
                .uri("/users/{id}", userId)
                .retrieve()
                .bodyToMono(User.class);
    }

}

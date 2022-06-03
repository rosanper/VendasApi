package com.letscode.saleapi.controller;

import com.letscode.saleapi.exceptions.BusinessException;
import com.letscode.saleapi.exceptions.ExceptionMessage;
import com.letscode.saleapi.exceptions.NotExistException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientException;

import java.time.Instant;

@RestControllerAdvice
@RequiredArgsConstructor
public class SaleAdviceController {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionMessage> businessException(BusinessException e){
        ExceptionMessage errorMessage = ExceptionMessage.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Erro regra de negócio.")
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(NotExistException.class)
    public ResponseEntity<ExceptionMessage> notExistException(NotExistException e){
        ExceptionMessage errorMessage = ExceptionMessage.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Objeto não encontrado")
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }


    @ExceptionHandler(WebClientException.class)
    public ResponseEntity<ExceptionMessage> clientException(WebClientException e){
        ExceptionMessage errorMessage = ExceptionMessage.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Problema com o cliente externo")
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(CallNotPermittedException.class)
    public ResponseEntity<ExceptionMessage> clientException(CallNotPermittedException e){
        ExceptionMessage errorMessage = ExceptionMessage.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Problema com o cliente externo - Circuit Breaker")
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}

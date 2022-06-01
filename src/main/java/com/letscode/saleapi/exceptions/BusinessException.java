package com.letscode.saleapi.exceptions;


public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}

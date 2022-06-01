package com.letscode.saleapi.exceptions;

public class NotExistException extends RuntimeException{
    public NotExistException(String message) {
        super(message);
    }
}

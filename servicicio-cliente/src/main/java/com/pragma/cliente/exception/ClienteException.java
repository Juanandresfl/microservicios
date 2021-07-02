package com.pragma.cliente.exception;

import org.springframework.http.HttpStatus;

public class ClienteException extends RuntimeException {
    private HttpStatus httpStatus;

    public ClienteException(HttpStatus httpStatus, String errorMessage) {
        super(errorMessage);
        this.httpStatus = httpStatus;
    }
}

package com.pragma.cliente.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ClienteException extends RuntimeException {
    private HttpStatus httpStatus;

    public ClienteException(HttpStatus httpStatus, String errorMessage) {
        super(errorMessage);
        this.httpStatus = httpStatus;
    }
}

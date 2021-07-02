package com.pragma.foto.exception;

import org.springframework.http.HttpStatus;

public class FotoException extends RuntimeException {
    private HttpStatus httpStatus;

    public FotoException(HttpStatus httpStatus, String errorMessage) {
        super(errorMessage);
        this.httpStatus = httpStatus;
    }
}

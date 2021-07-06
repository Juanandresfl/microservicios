package com.pragma.foto.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FotoException extends RuntimeException {
    private HttpStatus httpStatus;

    public FotoException(HttpStatus httpStatus, String errorMessage) {
        super(errorMessage);
        this.httpStatus = httpStatus;
    }
}

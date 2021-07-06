package com.pragma.foto.controllers;

import com.pragma.foto.exception.ErrorMensaje;
import com.pragma.foto.exception.FotoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ErrorController {
    private static final Map<String, Integer> STATUS = new HashMap<>();

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMensaje> handleAllExceptions(HttpServletRequest request, Exception exception) {
        ResponseEntity<ErrorMensaje> resultado;

        String excepcion = exception.getClass().getSimpleName();
        String mensaje = exception.getMessage();
        Integer codigo = getStatusCode(exception);

        if (codigo == null) {
            codigo = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }

        ErrorMensaje error = ErrorMensaje.builder().mensaje(mensaje).excepcion(excepcion)
                .url(request.getRequestURI()).status(codigo).build();
        resultado = new ResponseEntity<>(error, HttpStatus.valueOf(codigo));
        exception.printStackTrace();
        return resultado;
    }

    private Integer getStatusCode(Exception e) {
        if (e instanceof FotoException) {
            FotoException ex = (FotoException) e;
            if (ex.getHttpStatus() != null) {
                return ex.getHttpStatus().value();
            }
        }
        return STATUS.get(e.getClass().getSimpleName());
    }
}

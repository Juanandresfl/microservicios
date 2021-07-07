package com.pragma.foto.controllers;

import com.pragma.foto.exception.MensajeError;
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
    public final ResponseEntity<MensajeError> AllExceptions(HttpServletRequest request, Exception exception) {
        ResponseEntity<MensajeError> resultado;

        String excepcion = exception.getClass().getSimpleName();
        String mensaje = exception.getMessage();
        Integer codigo = getStatus(exception);

        if (codigo == null) {
            codigo = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }

        MensajeError error = MensajeError.builder().mensaje(mensaje).excepcion(excepcion)
                .url(request.getRequestURI()).status(codigo).build();
        resultado = new ResponseEntity<>(error, HttpStatus.valueOf(codigo));
        exception.printStackTrace();
        return resultado;
    }

    private Integer getStatus(Exception e) {
        if (e instanceof FotoException) {
            FotoException ex = (FotoException) e;
            if (ex.getHttpStatus() != null) {
                return ex.getHttpStatus().value();
            }
        }
        return STATUS.get(e.getClass().getSimpleName());
    }
}

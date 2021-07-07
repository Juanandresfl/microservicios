package com.pragma.cliente.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MensajeError {

    private String mensaje;

    private String excepcion;

    private String url;

    private int status;

}

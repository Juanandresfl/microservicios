package com.pragma.foto.exception;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMensaje {

    private String mensaje;

    private String excepcion;

    private String url;

    private int status;

}


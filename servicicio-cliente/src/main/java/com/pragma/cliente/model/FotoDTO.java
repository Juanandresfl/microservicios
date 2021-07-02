package com.pragma.cliente.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FotoDTO {
    private String id;
    private String nombre;
    private byte[] contenido;
    private long tamaño;
    private String tipoContenido;
}

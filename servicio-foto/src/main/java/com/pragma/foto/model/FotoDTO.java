package com.pragma.foto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FotoDTO {
    private String id;
    private String nombre;
    private byte[] contenido;
    private long tamaño;
    private String tipoContenido;
}

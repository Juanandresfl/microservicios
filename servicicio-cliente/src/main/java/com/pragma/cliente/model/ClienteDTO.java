package com.pragma.cliente.model;

import com.pragma.cliente.entities.TipoIdentificacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTO {
    private String identificacion;
    private TipoIdentificacion tipoIdentificacion;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String ciudad;
    private FotoDTO fotoDTO;
}

package com.pragma.foto.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "fotos")
public class Foto {
    @Id
    private String id;
    private String nombre;
    private byte[] contenido;
    private String tipoContenido;
}

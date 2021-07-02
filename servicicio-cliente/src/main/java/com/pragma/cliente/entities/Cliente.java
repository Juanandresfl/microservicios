package com.pragma.cliente.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    @Id
    private String identificacion;

    @ManyToOne
    @JoinColumn(name = "tipo_identificacion_id")
    private TipoIdentificacion tipoIdentificacion;

    private String nombre;
    private String apellido;

    private Integer edad;
    private String ciudad;

    @Column(name = "foto_id")
    private String fotoId;




}

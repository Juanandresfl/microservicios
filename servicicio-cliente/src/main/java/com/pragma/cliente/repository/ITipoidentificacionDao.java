package com.pragma.cliente.repository;

import com.pragma.cliente.entities.TipoIdentificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoidentificacionDao extends JpaRepository<TipoIdentificacion, Long> {
}

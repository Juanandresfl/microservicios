package com.pragma.cliente.repository;

import com.pragma.cliente.entities.Cliente;
import com.pragma.cliente.entities.TipoIdentificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteDao extends JpaRepository<Cliente, String> {

    public Cliente findByIdentificacionAndTipoIdentificacion(String identificacion,
                                                              TipoIdentificacion tipoIdentificacion);
}

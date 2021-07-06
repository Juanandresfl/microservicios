package com.pragma.cliente.repository;

import com.pragma.cliente.entities.Cliente;
import com.pragma.cliente.entities.TipoIdentificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IClienteDao extends JpaRepository<Cliente, String> {

    Cliente findByIdentificacionAndTipoIdentificacion(String identificacion,
                                                              TipoIdentificacion tipoIdentificacion);
    List<Cliente> findByEdadGreaterThanEqual(int edad);
}

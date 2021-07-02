package com.pragma.cliente.services;

import com.pragma.cliente.entities.TipoIdentificacion;

import java.util.List;

public interface TipoidentificacionService {
    public List<TipoIdentificacion> findAll();
    public TipoIdentificacion findById(Long id);
    public TipoIdentificacion save(TipoIdentificacion tipoIdentificacion);
    public void delete (Long id);
}

package com.pragma.cliente.services.impl;

import com.pragma.cliente.entities.TipoIdentificacion;
import com.pragma.cliente.repository.ITipoidentificacionDao;
import com.pragma.cliente.services.TipoidentificacionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TipoidentificacionServiceImpl implements TipoidentificacionService {

    @Autowired
    private ITipoidentificacionDao tipoidentificacionDao;

    @Override
    public List<TipoIdentificacion> findAll() {
        return tipoidentificacionDao.findAll();
    }

    @Override
    public TipoIdentificacion findById(Long id) {
        return tipoidentificacionDao.findById(id).orElse(null);
    }

    @Override
    public TipoIdentificacion save(TipoIdentificacion tipoIdentificacion) {
        return tipoidentificacionDao.save(tipoIdentificacion);
    }

    @Override
    public void delete(Long id) {
        tipoidentificacionDao.deleteById(id);
    }
}

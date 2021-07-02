package com.pragma.foto.repository;

import com.pragma.foto.entities.Foto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IFotoDao extends MongoRepository<Foto, String> {
    List<Foto>findById(List<String> id);
}

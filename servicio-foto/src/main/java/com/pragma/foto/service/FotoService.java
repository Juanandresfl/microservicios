package com.pragma.foto.service;

import com.pragma.foto.model.FotoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FotoService {

    List<FotoDTO> findAll();
//    List<FotoDTO> listByIds(List<String> ids);
    FotoDTO save(FotoDTO fotoDTO);
    FotoDTO findById(String id);
    FotoDTO update(String id,FotoDTO fotoDTO);
    void delete(String id);
}

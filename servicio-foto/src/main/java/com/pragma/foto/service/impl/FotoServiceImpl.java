package com.pragma.foto.service.impl;

import com.pragma.foto.entities.Foto;
import com.pragma.foto.exception.FotoException;
import com.pragma.foto.model.FotoDTO;
import com.pragma.foto.repository.IFotoDao;
import com.pragma.foto.service.FotoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FotoServiceImpl implements FotoService {

    @Autowired
    private IFotoDao fotoDao;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<FotoDTO> findAll() {
        List<FotoDTO> fotoDTOS = null;
        List<Foto> fotos = fotoDao.findAll();
        if(fotos.isEmpty()){
            throw new FotoException(HttpStatus.NO_CONTENT, "No se encuentran fotos en la bd");
        }
            fotoDTOS = fotos.stream().map(foto -> {
                return modelMapper.map(foto, FotoDTO.class);
            }).collect(Collectors.toList());

        return fotoDTOS;
    }

    @Override
    public FotoDTO save(FotoDTO fotoDTO) {
        Foto foto = modelMapper.map(fotoDTO,Foto.class);
        foto = fotoDao.save(foto);
        FotoDTO fotoDto = modelMapper.map(foto, FotoDTO.class);
        return fotoDto;
    }

    @Override
    public FotoDTO findById(String id) {
    FotoDTO fotoDTO = null;
    Foto foto = fotoDao.findById(id).orElse(null);
    if(foto == null){
        throw new FotoException(HttpStatus.NOT_FOUND , "La foto no existe en la bd");
    }
    fotoDTO = modelMapper.map(foto, FotoDTO.class);
        return fotoDTO;
    }

    @Override
    public FotoDTO update(String id,FotoDTO fotoDTO) {
        Foto foto = fotoDao.findById(id).orElse(null);
        if(foto == null){
            throw new FotoException(HttpStatus.NOT_FOUND,"La foto no existe");
        }
        foto = modelMapper.map(fotoDTO, Foto.class);
        fotoDao.save(foto);
        return fotoDTO;
    }

    @Override
    public void delete(String id) {
        Foto foto = fotoDao.findById(id).orElse(null);
        if(foto == null){
            throw new FotoException(HttpStatus.BAD_REQUEST, "La foto no existe en la bd");
        }
        fotoDao.delete(foto);
    }
}

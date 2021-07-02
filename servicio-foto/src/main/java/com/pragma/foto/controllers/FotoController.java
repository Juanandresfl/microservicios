package com.pragma.foto.controllers;

import com.pragma.foto.model.FotoDTO;
import com.pragma.foto.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/fotos")
public class FotoController {

    @Autowired
    private FotoService fotoService;

    @GetMapping
    public ResponseEntity<List<FotoDTO>> listar(){
        List<FotoDTO> fotos = fotoService.findAll();
        return ResponseEntity.ok(fotos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FotoDTO> buscar(@PathVariable String id){
        FotoDTO fotoDTO = fotoService.findById(id);
        return ResponseEntity.ok(fotoDTO);
    }

    @PostMapping
    public ResponseEntity<FotoDTO> registrar(@RequestBody FotoDTO fotoDTO){
        FotoDTO fotoDto = fotoService.save(fotoDTO);
        return ResponseEntity.ok(fotoDto);
    }

    @PostMapping("/cargar")
    public ResponseEntity<FotoDTO> registrar(@RequestParam("file") MultipartFile file) throws IOException {
        FotoDTO fotoDTO = fotoService.save(file);
        return ResponseEntity.ok(fotoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FotoDTO> actualizar(@PathVariable String id, @RequestBody FotoDTO fotoDTO){
        FotoDTO fotoDto = fotoService.update(id,fotoDTO);
        return ResponseEntity.ok(fotoDto);
    }

    @PutMapping("cargar/{id}")
    public ResponseEntity<FotoDTO> actualizar(@PathVariable String id, @RequestParam("file") MultipartFile file) throws IOException {
        FotoDTO fotoDTO = fotoService.update(id,file);
        return ResponseEntity.ok(fotoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable String id){
        fotoService.delete(id);
        return ResponseEntity.ok().build();
    }
}

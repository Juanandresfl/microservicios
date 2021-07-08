package com.pragma.foto.controllers;

import com.pragma.foto.model.FotoDTO;
import com.pragma.foto.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fotos")
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST , RequestMethod.DELETE, RequestMethod.PUT})
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

    @PostMapping("/ids")
    public ResponseEntity<List<FotoDTO>> listarPorIds(@RequestBody (required = true) List<String> ids){
        List<FotoDTO> fotos = fotoService.listByIds(ids);
        return ResponseEntity.ok(fotos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FotoDTO> actualizar(@PathVariable String id, @RequestBody FotoDTO fotoDTO){
        FotoDTO fotoDto = fotoService.update(id,fotoDTO);
        return ResponseEntity.ok(fotoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable String id){
        fotoService.delete(id);
        return ResponseEntity.ok().build();
    }
}

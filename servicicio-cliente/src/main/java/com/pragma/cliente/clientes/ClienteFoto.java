package com.pragma.cliente.clientes;

import com.pragma.cliente.model.FotoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "servicio-foto", path = "/fotos" , fallback = ClienteHystrixFallback.class)
public interface ClienteFoto {

    @GetMapping
    public ResponseEntity<List<FotoDTO>> listar();

    @GetMapping("/{id}")
    public ResponseEntity<FotoDTO> buscar(@PathVariable String id);

    @PostMapping
    public ResponseEntity<FotoDTO> registrar(@RequestBody FotoDTO fotoDTO);

    @PostMapping("/ids")
    public ResponseEntity<List<FotoDTO>> listarPorIds(@RequestBody List<String> ids);

    @PutMapping("/{id}")
    public ResponseEntity<FotoDTO> actualizar(@PathVariable String id,@RequestBody FotoDTO fotoDTO);

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable String id);
}

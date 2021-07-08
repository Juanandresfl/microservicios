package com.pragma.cliente.clientes;

import com.pragma.cliente.model.FotoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClienteHystrixFallback implements ClienteFoto{
    @Override
    public ResponseEntity<List<FotoDTO>> listar() {
        return ResponseEntity.ok(new ArrayList<FotoDTO>());
    }

    @Override
    public ResponseEntity<FotoDTO> buscar(String id) {
        return ResponseEntity.ok(new FotoDTO());
    }

    @Override
    public ResponseEntity<FotoDTO> registrar(FotoDTO fotoDTO) {
        return ResponseEntity.ok(new FotoDTO());
    }

    @Override
    public ResponseEntity<List<FotoDTO>> listarPorIds(List<String> ids) {
        List<FotoDTO> fotos = new ArrayList<>();
        return ResponseEntity.ok(fotos);
    }

    @Override
    public ResponseEntity<FotoDTO> actualizar(String id, FotoDTO fotoDTO) {
        return ResponseEntity.ok(new FotoDTO());
    }

    @Override
    public ResponseEntity<?> eliminar(String id) {
        return ResponseEntity.ok().build();
    }
}

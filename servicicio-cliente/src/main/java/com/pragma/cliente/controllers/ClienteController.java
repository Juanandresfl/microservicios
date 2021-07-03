package com.pragma.cliente.controllers;

import com.pragma.cliente.entities.Cliente;
import com.pragma.cliente.model.ClienteDTO;
import com.pragma.cliente.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> listarClientes(){
        return ResponseEntity.ok(clienteService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> guardarCliente(@RequestBody ClienteDTO cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body( clienteService.save(cliente));
    }

    @PutMapping
    public ResponseEntity<?> actualizarCliente(@RequestBody ClienteDTO cliente){
        ClienteDTO clienteDTO = clienteService.update(cliente);
        return ResponseEntity.ok(clienteDTO);
    }

    @GetMapping("/{identificacion}")
    public ResponseEntity<?> buscarPorIdentificacion(@PathVariable String identificacion){
        ClienteDTO clienteDTO = clienteService.findById(identificacion);
        return ResponseEntity.ok(clienteDTO);
    }

    @DeleteMapping
    public ResponseEntity<?> eliminarCliente(String identificacion){
       clienteService.delete(identificacion);
        return ResponseEntity.ok().build();
    }
}

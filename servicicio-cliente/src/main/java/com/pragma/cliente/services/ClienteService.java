package com.pragma.cliente.services;


import com.pragma.cliente.model.ClienteDTO;
import java.util.List;

public interface ClienteService {
     List<ClienteDTO> findAll();
     ClienteDTO findById(String identificacion);
     ClienteDTO save (ClienteDTO clienteDTO);
     ClienteDTO update(ClienteDTO clienteDTO);
     List<ClienteDTO> filter(int edad);
     void delete (String identificacion);
}

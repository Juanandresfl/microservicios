package com.pragma.cliente.services;


import com.pragma.cliente.model.ClienteDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ClienteService {
     List<ClienteDTO> findAll();
     ClienteDTO findById(String identificacion);
     ClienteDTO save ( ClienteDTO clienteDTO);
     void delete (String identificacion);
}

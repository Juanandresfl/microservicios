package com.pragma.cliente.services.impl;

import com.pragma.cliente.clientes.ClienteFoto;
import com.pragma.cliente.entities.Cliente;
import com.pragma.cliente.entities.TipoIdentificacion;
import com.pragma.cliente.exception.ClienteException;
import com.pragma.cliente.model.ClienteDTO;
import com.pragma.cliente.model.FotoDTO;
import com.pragma.cliente.repository.IClienteDao;
import com.pragma.cliente.repository.ITipoidentificacionDao;
import com.pragma.cliente.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service

@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteFoto clienteFoto;

    @Autowired
    private IClienteDao clienteDao;

    @Autowired
    private ITipoidentificacionDao tipoidentificacionDao;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<ClienteDTO> findAll() {
        List<Cliente> clientes = clienteDao.findAll();
        return findClientes(clientes);
    }

    private List<ClienteDTO> findClientes(List<Cliente> clientes){
        ClienteDTO clienteDTO = null;
        List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();
            List<FotoDTO> fotos = clienteFoto.listar().getBody();
            for (int i = 0;i < fotos.size(); i++){
                for(int j=0; j<clientes.size();j++) {
                    if (clientes.get(j).getFotoId().equals(fotos.get(i).getId())) {
                        clienteDTO = modelMapper.map(clientes.get(j), ClienteDTO.class);
                        clienteDTO.setFotoDTO(fotos.get(i));
                        clientesDTO.add(clienteDTO);
                    }
                }
            }
            return clientesDTO;
        }


    @Override
    public ClienteDTO findById(String identificacion) {
        ClienteDTO clienteDTO = null;
        Cliente cliente  = clienteDao.findById(identificacion).orElse(null);
        if(cliente == null){
            throw new ClienteException(HttpStatus.NOT_FOUND, "cliente no existe en la bd");
        }
        clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
        if(cliente.getFotoId() != null){
            FotoDTO fotoDTO = clienteFoto.buscar(cliente.getFotoId()).getBody();
            clienteDTO.setFotoDTO(fotoDTO);
        }
        return clienteDTO;
    }

    @Override
    public ClienteDTO save(ClienteDTO clienteDTO) {
        FotoDTO foto = null;
        System.out.println("CLIENTE ID: "+ clienteDTO.getIdentificacion());
        Cliente cliente = clienteDao.findByIdentificacionAndTipoIdentificacion(clienteDTO.getIdentificacion(),
                TipoIdentificacion.builder().id(clienteDTO.getTipoIdentificacion().getId()).build());
        if(cliente != null){
            throw new ClienteException(HttpStatus.BAD_REQUEST, "El cliente ya existe en la bd");
        }
        cliente = modelMapper.map(clienteDTO,Cliente.class);
        if(clienteDTO.getFotoDTO() != null){
            foto = clienteFoto.registrar(clienteDTO.getFotoDTO()).getBody();
            if(foto!= null){
                cliente.setFotoId(foto.getId());
            }
        }
        clienteDao.save(cliente);
        clienteDTO = modelMapper.map(cliente,ClienteDTO.class);
        clienteDTO.setFotoDTO(foto);
        return clienteDTO;
    }

    @Override
    public void delete(String identificacion) {
        Cliente cliente = clienteDao.findById(identificacion).orElse(null);
        if(cliente == null){
            throw new ClienteException(HttpStatus.NOT_FOUND , "El cliente no existe en la bd");
        }
        clienteDao.delete(cliente);
    }
}

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
import org.springframework.beans.factory.annotation.Autowired;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

//@RequiredArgsConstructor
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

    private List<ClienteDTO> findClientes(List<Cliente> clientes) {
        ClienteDTO clienteDTO = null;
        List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();
        List<FotoDTO> fotos = clienteFoto.listar().getBody();
        for (int i = 0; i < fotos.size(); i++) {
            for (int j = 0; j < clientes.size(); j++) {
                if (clientes.get(j).getFotoId().equals(fotos.get(i).getId())) {
                    clienteDTO = modelMapper.map(clientes.get(j), ClienteDTO.class);
                    clienteDTO.setFoto(fotos.get(i));
                    clientesDTO.add(clienteDTO);
                }
            }
        }
        return clientesDTO;
    }


    @Override
    public ClienteDTO findById(String identificacion) {
        ClienteDTO clienteDTO = null;
        Cliente cliente = clienteDao.findById(identificacion).orElse(null);
        if (cliente == null) {
            throw new ClienteException(HttpStatus.NOT_FOUND, "cliente no existe en la bd");
        }
        clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
        if (cliente.getFotoId() != null) {
            FotoDTO fotoDTO = clienteFoto.buscar(cliente.getFotoId()).getBody();
            clienteDTO.setFoto(fotoDTO);
        }
        return clienteDTO;
    }

    @Override
    public ClienteDTO save(ClienteDTO clienteDTO) {
        FotoDTO foto = null;
        Cliente cliente = clienteDao.findByIdentificacionAndTipoIdentificacion(clienteDTO.getIdentificacion(),
                TipoIdentificacion.builder().id(clienteDTO.getTipoIdentificacion().getId()).build());
        if (cliente != null) {
            throw new ClienteException(HttpStatus.BAD_REQUEST, "El cliente ya existe en la bd");
        }
        cliente = modelMapper.map(clienteDTO, Cliente.class);
        if (clienteDTO.getFoto() == null) {
            throw new ClienteException(HttpStatus.BAD_REQUEST, "El cliente no se puede guardar sin foto");
        }
        foto = clienteFoto.registrar(clienteDTO.getFoto()).getBody();
        cliente.setFotoId(foto.getId());
        clienteDao.save(cliente);
        clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
        clienteDTO.setFoto(foto);
        return clienteDTO;
    }

    @Override
    public ClienteDTO update(ClienteDTO clienteDTO) {
        FotoDTO foto = null;
        Cliente cliente = clienteDao.findById(clienteDTO.getIdentificacion()).orElse(null);
        System.out.println("TIPO :" + cliente.getTipoIdentificacion().getNombre());
        if (cliente == null) {
            throw new ClienteException(HttpStatus.BAD_REQUEST, "El cliente no existe en la bd");
        }
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setCiudad(clienteDTO.getCiudad());
        cliente.setEdad(clienteDTO.getEdad());

        if (clienteDTO.getFoto() != null) {
            foto = clienteFoto.buscar(cliente.getFotoId()).getBody();
            foto.setContenido(clienteDTO.getFoto().getContenido());
            foto.setNombre(clienteDTO.getFoto().getNombre());
            foto.setTipoContenido(clienteDTO.getFoto().getTipoContenido());
            clienteFoto.actualizar(foto.getId(), foto);
            cliente.setFotoId(foto.getId());
        }
        clienteDao.save(cliente);
        clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
        clienteDTO.setFoto(foto);
        return clienteDTO;
    }

    @Override
    public List<ClienteDTO> filter(int edad) {
        List<Cliente> clientes = null;
        clientes = clienteDao.findByEdadGreaterThanEqual(edad);
        if (clientes.isEmpty()) {
            throw new ClienteException(HttpStatus.NOT_FOUND, "No hay clientes en ese rango de edad");
        }
        return findClientes(clientes);
    }

    @Override
    public void delete(String identificacion) {
        Cliente cliente = clienteDao.findById(identificacion).orElse(null);
        if (cliente == null) {
            throw new ClienteException(HttpStatus.NOT_FOUND, "El cliente no existe en la bd");
        }

        clienteFoto.eliminar(cliente.getFotoId());
        clienteDao.delete(cliente);
    }
}

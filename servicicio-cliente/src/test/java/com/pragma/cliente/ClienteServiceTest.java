package com.pragma.cliente;

import com.pragma.cliente.clientes.ClienteFoto;
import com.pragma.cliente.entities.Cliente;
import com.pragma.cliente.entities.TipoIdentificacion;
import com.pragma.cliente.exception.ClienteException;
import com.pragma.cliente.model.ClienteDTO;
import com.pragma.cliente.model.FotoDTO;
import com.pragma.cliente.repository.IClienteDao;
import com.pragma.cliente.services.impl.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static com.pragma.cliente.Datos.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    IClienteDao clienteDao;

    @Mock
    ClienteFoto clienteFoto;

    @InjectMocks
    ClienteServiceImpl clienteService;

    @BeforeEach
    void config(){
        clienteService = new ClienteServiceImpl(clienteFoto, clienteDao);
    }

    @Test
    void testListarClientes(){
        Mockito.when(clienteFoto.listarPorIds(listaIds()))
                .thenReturn((ResponseEntity<List<FotoDTO>>) ResponseEntity.ok(listAllFotos()));
        Mockito.when(clienteDao.findAll()).thenReturn(listAllClientes());

        List<ClienteDTO> service = clienteService.findAll();

        assertEquals(2,service.size());
        assertEquals("01", service.stream().filter(clienteDTO -> clienteDTO.getIdentificacion()
        .equals("12345"))
        .findFirst().get().getFoto().getId());

        assertEquals("02", service.stream().filter(clienteDTO -> clienteDTO.getIdentificacion()
                .equals("12346"))
                .findFirst().get().getFoto().getId());
    }

    @Test
    void testBuscarClientePorId(){
        Mockito.when(clienteDao.findById("12345"))
                .thenReturn(java.util.Optional.ofNullable(getCliente()));
        Mockito.when(clienteFoto.buscar("01"))
                .thenReturn(ResponseEntity.ok(getFoto()));

        ClienteDTO cliente = clienteService.findById("12345");

        assertEquals("12345", cliente.getIdentificacion());
        assertEquals("prueba", cliente.getNombre());
        assertEquals("01", cliente.getFoto().getId());
    }

    @Test
    void testClienteExistente(){
        Mockito.when(clienteDao.findByIdentificacionAndTipoIdentificacion("12345",
                TipoIdentificacion.builder().id(1L).build()))
                .thenReturn(getCliente());

        assertThrows(ClienteException.class, () ->{
            clienteService.save(getClienteDTO());
        });
        verify(clienteDao).findByIdentificacionAndTipoIdentificacion(anyString(),
                any(TipoIdentificacion.class));
    }

    @Test
    void testGuardarCliente(){
        Mockito.when(clienteDao.findByIdentificacionAndTipoIdentificacion("12345",
                TipoIdentificacion.builder().id(1L).build()))
                .thenReturn(null);
        Mockito.when(clienteDao.save(any(Cliente.class))).thenReturn(getCliente());
        Mockito.when(clienteFoto.registrar(any(FotoDTO.class))).thenReturn(ResponseEntity.ok(getFoto()));

        ClienteDTO clienteDTO = clienteService.save(getClienteDTO());
        assertNotNull(clienteDTO);
        assertNotNull(clienteDTO.getFoto());
        assertEquals("01", clienteDTO.getFoto().getId());
        verify(clienteDao).save(any(Cliente.class));
        verify(clienteFoto).registrar(any(FotoDTO.class));
    }

     @Test
    void testEliminarCliente(){
        Mockito.when(clienteDao.findById("12345"))
                .thenReturn(java.util.Optional.ofNullable(getCliente()));

        clienteService.delete("12345");

        verify(clienteDao).findById(anyString());
        verify(clienteDao).delete(any(Cliente.class));
        verify(clienteFoto).eliminar(anyString());
     }

     @Test
    void testFiltrarPorEdad(){
         List<Cliente> clientes = Arrays.asList(getCliente(), getCliente2());

         Mockito.when(clienteFoto.listarPorIds(listaIds()))
                 .thenReturn((ResponseEntity<List<FotoDTO>>) ResponseEntity.ok(listAllFotos()));
        Mockito.when(clienteDao.findByEdadGreaterThanEqual(20)).thenReturn(clientes);

        List<ClienteDTO> lista = clienteService.filter(20); //2

         assertEquals(2, lista.size());
         verify(clienteDao).findByEdadGreaterThanEqual(anyInt());
     }

     @Test
    void testActualizarCliente(){
        Mockito.when(clienteDao.findById("12345"))
                .thenReturn(java.util.Optional.ofNullable(getCliente()));
        Mockito.when(clienteDao.save(any(Cliente.class))).thenReturn(getCliente());

        Mockito.when(clienteFoto.buscar(anyString())).thenReturn(ResponseEntity.ok(getFoto()));
        Mockito.when(clienteFoto.actualizar(anyString(), any(FotoDTO.class))).thenReturn(ResponseEntity.ok(getFoto()));

        ClienteDTO clienteDTO = clienteService.update(getClienteDTO());

        assertNotNull(clienteDTO);
        assertNotNull(clienteDTO.getFoto());

        assertEquals("01", clienteDTO.getFoto().getId());
        assertEquals("prueba", clienteDTO.getNombre());

        verify(clienteDao).save(any(Cliente.class));
        verify(clienteFoto).buscar(anyString());
        verify(clienteFoto).actualizar(anyString(), any(FotoDTO.class));
     }
}

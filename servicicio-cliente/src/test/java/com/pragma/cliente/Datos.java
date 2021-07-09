package com.pragma.cliente;

import com.pragma.cliente.entities.Cliente;
import com.pragma.cliente.entities.TipoIdentificacion;
import com.pragma.cliente.model.ClienteDTO;
import com.pragma.cliente.model.FotoDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Datos {

    public static FotoDTO getFoto(){
        return FotoDTO.builder().id("01")
                .contenido(null)
                .tipoContenido("image/png")
                .nombre("prueba")
                .build();
    }

    public static FotoDTO getFoto2(){
        return FotoDTO.builder().id("02")
                .contenido(null)
                .tipoContenido("image/jpg")
                .nombre("prueba2")
                .build();
    }

    public static Cliente getCliente(){
        return Cliente.builder().identificacion("12345")
                .nombre("prueba")
                .apellido("pragma")
                .edad(20)
                .ciudad("medellin")
                .tipoIdentificacion(TipoIdentificacion.builder().id(1L).build())
                .fotoId("01")
                .build();
    }

    public static ClienteDTO getClienteDTO(){
        return ClienteDTO.builder().identificacion("12345")
                .nombre("prueba")
                .apellido("pragma")
                .edad(20)
                .ciudad("medellin")
                .tipoIdentificacion(TipoIdentificacion.builder().id(1L).build())
                .foto(getFoto())
                .build();
    }

    public static Cliente getCliente2(){
        return Cliente.builder().identificacion("12346")
                .nombre("prueba2")
                .apellido("pragma2")
                .edad(22)
                .ciudad("manizales")
                .tipoIdentificacion(TipoIdentificacion.builder().id(2L).build())
                .fotoId("02")
                .build();
    }

    public static List<FotoDTO> listAllFotos(){
        return Arrays.asList(getFoto(), getFoto2());
    }

    public static List<Cliente> listAllClientes(){
        return Arrays.asList(getCliente(),getCliente2());
    }

    public static List<String> listaIds(){
        List<String> ids = new ArrayList<>();
        ids.add("01");
        ids.add("02");
        return ids;
    }
}

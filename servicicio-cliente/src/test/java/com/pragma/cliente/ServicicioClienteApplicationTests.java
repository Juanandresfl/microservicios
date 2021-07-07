package com.pragma.cliente;

import com.pragma.cliente.model.ClienteDTO;
import com.pragma.cliente.services.ClienteService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServicicioClienteApplicationTests {

	@Autowired
	private ClienteService clienteService;

	@Test
	public void findByIdentification(){
		ClienteDTO cliente = clienteService.findById("1090527947");
		Assertions.assertThat(cliente.getNombre()).isEqualTo("juan");
	}

}

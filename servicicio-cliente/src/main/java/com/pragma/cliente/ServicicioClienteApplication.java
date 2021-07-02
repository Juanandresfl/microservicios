package com.pragma.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicicioClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicicioClienteApplication.class, args);
	}

}

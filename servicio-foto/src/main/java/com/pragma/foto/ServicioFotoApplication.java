package com.pragma.foto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ServicioFotoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioFotoApplication.class, args);
	}

}

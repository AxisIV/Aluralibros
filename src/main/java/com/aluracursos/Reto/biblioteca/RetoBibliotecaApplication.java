package com.aluracursos.Reto.biblioteca;

import com.aluracursos.Reto.biblioteca.principal.Principal;
import com.aluracursos.Reto.biblioteca.repository.AutorRepository;
import com.aluracursos.Reto.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RetoBibliotecaApplication implements CommandLineRunner {

	@Autowired()
	private LibroRepository repository;
	private AutorRepository autorRepository;

	public static void main(String[] args) {

		SpringApplication.run(RetoBibliotecaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository,autorRepository);
		principal.muestraElMenu();
	}


}

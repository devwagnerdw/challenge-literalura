package com.challengeliteratura.challengeliteratura;

import com.challengeliteratura.challengeliteratura.controller.LiteraturaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.challengeliteratura.challengeliteratura.repository.AutorRepository;
import com.challengeliteratura.challengeliteratura.repository.LivroRepository;

@SpringBootApplication
public class ChallengeLiteraturaApplication implements CommandLineRunner {

	@Autowired
	private LivroRepository livroRepositorio;
	@Autowired
	private AutorRepository autorRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		LiteraturaController client = new LiteraturaController(livroRepositorio, autorRepositorio);
		client.menu();
	}

}

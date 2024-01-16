package br.com.persisteJpa;

import br.com.persisteJpa.application.App;
import br.com.persisteJpa.repository.EpisodeRepository;
import br.com.persisteJpa.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersisteJpaApplication implements CommandLineRunner {

	@Autowired
	private SerieRepository SerieRep;

	@Autowired
	private EpisodeRepository EpisodeRep;

	public static void main(String[] args) {
		SpringApplication.run(PersisteJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println();
		new App(SerieRep,EpisodeRep).run();
	}
}

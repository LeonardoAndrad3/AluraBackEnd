package br.com.finderMusic;

import br.com.finderMusic.application.App;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class FinderMusicApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FinderMusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		new App().run();
	}
}

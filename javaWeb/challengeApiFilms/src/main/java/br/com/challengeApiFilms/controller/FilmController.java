package br.com.challengeApiFilms.controller;

import br.com.challengeApiFilms.dto.FilmDTO;
import br.com.challengeApiFilms.repository.FilmRepository;
import br.com.challengeApiFilms.service.FilmService;
import jakarta.persistence.GeneratedValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/series")
public class FilmController {

    private FilmService service;

    FilmController(FilmService service){
        this.service = service;
    }

    @GetMapping("/frases")
    public ResponseEntity<FilmDTO> getWordById(){
        return ResponseEntity.ok().body(service.findRandom());
    }
}

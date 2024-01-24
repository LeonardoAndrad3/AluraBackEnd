package br.com.challengeApiFilms.service;

import br.com.challengeApiFilms.dto.FilmDTO;
import br.com.challengeApiFilms.entity.Film;
import br.com.challengeApiFilms.repository.FilmRepository;
import br.com.challengeApiFilms.service.exceptions.NotFoundException;
import br.com.challengeApiFilms.util.ConverterManager;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

    private FilmRepository repo;

    public FilmService(FilmRepository repo){
        this.repo = repo;
    }

    public Film save(Film film){
        return repo.save(film);
    }

    public FilmDTO findById(Long id){
        return repo.findById(id).map(ConverterManager::toFilm).orElseThrow(() -> new NotFoundException("Not found this id"));
    }

    public FilmDTO findRandom() {
        return ConverterManager.toFilm(repo.findRandomFilm());
    }
}

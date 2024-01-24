package br.com.challengeApiFilms.util;


import br.com.challengeApiFilms.dto.FilmDTO;
import br.com.challengeApiFilms.entity.Film;
import br.com.challengeApiFilms.model.DataFilm;

public class ConverterManager {

    public static FilmDTO toFilm(Film film){
        return new FilmDTO(film.getTitle(),film.getWord(),film.getPerson(),film.getPoster());
    }


}

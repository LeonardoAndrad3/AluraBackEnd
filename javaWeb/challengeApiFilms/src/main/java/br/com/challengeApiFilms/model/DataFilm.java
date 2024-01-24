package br.com.challengeApiFilms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataFilm(
        String title,
        String word,
        String person,
        String poster
) {
}

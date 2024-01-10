package br.com.persisteJpa.model.records;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DataEpisode(
        @JsonAlias("Title") String title,
        @JsonAlias("Episode") Integer number,
        @JsonAlias("imdbRating") String rating,
        @JsonAlias("Released") String release) {
}

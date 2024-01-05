package br.com.springProject.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataEp(
        @JsonAlias("Title") String title,
        @JsonAlias("Episode") Integer ep,
        @JsonAlias("imdbRating") String rating,
        @JsonAlias("Released") String release) {
}

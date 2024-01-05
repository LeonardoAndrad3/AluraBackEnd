package br.com.springProject.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSerie(
        @JsonAlias("Title") String title,
        @JsonAlias("totalSeasons") Integer seasons,
        @JsonAlias("imdbVotes") String rating
){}




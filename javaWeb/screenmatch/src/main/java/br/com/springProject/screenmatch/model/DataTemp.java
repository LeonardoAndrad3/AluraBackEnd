package br.com.springProject.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataTemp(
        @JsonAlias("Season") Integer season,
        @JsonAlias("Episodes")List<DataEp> episodes
        ) {
}

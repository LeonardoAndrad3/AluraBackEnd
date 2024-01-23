package br.com.persisteJpa.dto;

import java.time.LocalDate;

public record EpisodeDTO(String title,
    Integer numberEpisode,
    Integer season
){}

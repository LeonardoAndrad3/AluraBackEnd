package br.com.springProject.screenmatch.dto;

import br.com.springProject.screenmatch.entity.Episode;
import br.com.springProject.screenmatch.entity.Serie;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

public record SerieDTO(
        String title,
        Integer seasons,
        String rating
) {
}

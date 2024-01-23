package br.com.persisteJpa.dto;

import br.com.persisteJpa.model.enums.Categorie;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record SerieDTO(
        Long id,
        String title,
        Integer totalSeasons,
        Double rating,
        Categorie genre,
        String actors,
        String poster,
        String plot
) {
}

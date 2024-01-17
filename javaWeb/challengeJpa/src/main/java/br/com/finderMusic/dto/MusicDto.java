package br.com.finderMusic.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nonnull;
import org.springframework.lang.NonNull;


@JsonIgnoreProperties(ignoreUnknown = true)
public record MusicDto(
        @JsonAlias("desc") String title,
        @JsonAlias("band") String name
) {
}

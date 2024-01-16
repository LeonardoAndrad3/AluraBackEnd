package br.com.finderMusic.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Docs(
        @JsonAlias("docs") List<MusicDto> musicDtos
) {
}

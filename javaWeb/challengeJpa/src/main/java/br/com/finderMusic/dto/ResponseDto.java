package br.com.finderMusic.dto;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseDto<T>(
        @JsonAlias("response") Docs<T> response,
        @JsonAlias("artist") List<ArtistDto> artist
) {
}

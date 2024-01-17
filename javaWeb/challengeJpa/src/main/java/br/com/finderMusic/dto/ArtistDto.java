package br.com.finderMusic.dto;

import br.com.finderMusic.enums.Career;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record ArtistDto(
    @JsonAlias("desc") String name,
    @JsonAlias("toplyrics") List<MusicDto> toplyrics,
    @JsonAlias("pic_medium") String photo

) {
}

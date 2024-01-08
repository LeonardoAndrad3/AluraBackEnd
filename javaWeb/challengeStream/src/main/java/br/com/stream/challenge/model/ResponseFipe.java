package br.com.stream.challenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseFipe(
        @JsonAlias("codigo") String code,
        @JsonAlias("nome") String tag) {
}

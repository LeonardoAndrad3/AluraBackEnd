package br.com.stream.challenge.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VeiculoFipe(
        @JsonAlias("valor") String valor,
        @JsonAlias("marca") String marca,
        @JsonAlias("modelo") String modelo,
        @JsonAlias("anomodelo") Integer anoModelo,
        @JsonAlias("combustivel") String combustivel,
        @JsonAlias("codigofipe") String codigoFipe,
        @JsonAlias("mesreferencia") String mesReferencia,
        @JsonAlias("siglacombustivel") Character siglaCombustivel) {
}

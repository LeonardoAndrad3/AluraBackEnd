package br.com.alura.desafios.segundoDesafio;

import br.com.alura.desafios.segundoDesafio.entities.Music;
import br.com.alura.desafios.segundoDesafio.entities.PodCasts;
import br.com.alura.desafios.segundoDesafio.resource.FilterClas;

public class Application {

    public static void main(String[] args) {

        Music m1 = new Music("Good day", 4, 200000, 120000, 20, "Leonardo", "pop");
        PodCasts p1 = new PodCasts("How are you day?", 90, 500, 450, 300, "Antônio", "Paulo", "What's developer day");
        FilterClas filterClas = new FilterClas();

        filterClas.filter(m1);
        filterClas.filter(p1);

    }

}

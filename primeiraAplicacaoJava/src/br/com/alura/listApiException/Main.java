package br.com.alura.listApiException;

import br.com.alura.aula.resource.Episode;
import br.com.alura.aula.resource.Film;
import br.com.alura.aula.resource.Serie;
import br.com.alura.aula.resource.Title;
import br.com.alura.aula.resource.math.CalcTime;
import br.com.alura.aula.resource.math.RecommendationFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {


    public static void main(String[] args) {
        Film filmMe = new Film("Top gun", 1980, true, 4.8, 100, 135, "Leonardo");
        Film film2 = new Film("Top gunnn", 1990, true, 4.2, 40, 105, "Nando");
        Serie serie = new Serie("How I meet your mother", 2003, false, 4.0, 245, 20, 10, false, 10, 50);
        Film filmePaulo = new Film("Nomad", 2018, false, 4.8, 100, 200, "Paulo");
        Film f1 = filmePaulo;


        ArrayList<Title> list = new ArrayList<>();

        list.add(filmePaulo);
        list.add(filmMe);
        list.add(film2);
        list.add(serie);

        for (Title item : list) {
            System.out.println(item.getName());

            if (item instanceof Film film && film.getClassification() > 2) {
                System.out.println("Classification: " + film.getClassification());
            }

        }

        ArrayList<String> findArtist =  new ArrayList<>();

        findArtist.add("Adam");
        findArtist.add("Paulo");
        findArtist.add("Jacqueline");
        System.out.println(findArtist);

        //Ordenação
        Collections.sort(findArtist);
        System.out.println("List sort: ");
        System.out.println(findArtist);

        Collections.sort(list);

        System.out.println(list);

        //Comparator é uma classe de comparação
        list.sort(Comparator.comparing(Title::getReleaseYear));


        System.out.println("Comparing with release:");
        System.out.println(list);

    }

}

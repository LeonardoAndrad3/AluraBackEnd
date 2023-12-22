package br.com.alura.programacaoOrientadeObjeto;

import br.com.alura.aula.resource.Episode;
import br.com.alura.aula.resource.Film;
import br.com.alura.aula.resource.Serie;
import br.com.alura.aula.resource.math.CalcTime;
import br.com.alura.aula.resource.math.RecommendationFilter;

import java.util.logging.Filter;

public class Main {

    public static void main(String[] args) {

        Film filmMe = new Film("Top gun", 1980, true, 4.8, 100, 135, "Leonardo");
        Film film2 = new Film("Top gunnn", 1990, true, 4.2, 40, 105, "Nando");

        filmMe.getTechnicRecord();
        filmMe.rate(8);
        filmMe.rate(5);
        filmMe.rate(10);

        System.out.println("Total rating: "+filmMe.getTotalRating());
        System.out.println("Rating: "+filmMe.getRating());

        Serie serie = new Serie("How I meet your mother", 2003, false, 4.0, 245, 20, 10, false, 10, 50);

        System.out.println();
        serie.getTechnicRecord();
        System.out.println(serie.getDurationInMinutes());

        CalcTime calc = new CalcTime();

        calc.includ(filmMe);
        calc.includ(film2);
        calc.includ(serie);
        System.out.println(calc.getTotalTime());

        RecommendationFilter filter = new RecommendationFilter();
        System.out.println(filmMe.getClassification());
        filter.filtrar(filmMe);

        Episode ep1 = new Episode();

        ep1.setNumber(1);
        ep1.setSerie(serie);
        ep1.setName("I travel to New York");
        ep1.setTotalVisualization(100);

        filter.filtrar(ep1);

    }

}

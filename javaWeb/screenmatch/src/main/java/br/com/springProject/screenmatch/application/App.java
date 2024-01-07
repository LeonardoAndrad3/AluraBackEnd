package br.com.springProject.screenmatch.application;

import br.com.springProject.screenmatch.model.DataEp;
import br.com.springProject.screenmatch.model.DataSerie;
import br.com.springProject.screenmatch.model.DataTemp;
import br.com.springProject.screenmatch.model.Episode;
import br.com.springProject.screenmatch.service.ConvertData;
import br.com.springProject.screenmatch.service.GetApi;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class App {

    private Scanner sc = new Scanner(System.in);
    private GetApi api = new GetApi();
    private ConvertData converter = new ConvertData();
    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=d447ad04";

    public void showMenu(){
        System.out.println("Write the name of serie: ");
        var search = sc.nextLine();
        var strRequest = String.format("%s%s%s", ADDRESS, search.replace(" ", "+"), APIKEY);
        var json = api.getData(strRequest);
        var serie = converter.getDados(json, DataSerie.class);
        System.out.println(serie);

        List<DataTemp> temps = new ArrayList<>();

		for(int i = 1; i <= serie.seasons(); i++){
			 json = api.getData(String.format("%s%s&Season=%d%s", ADDRESS, search.replace(" ", "+"), i, APIKEY));
			 DataTemp dataTemp =  converter.getDados(json, DataTemp.class);
			 temps.add(dataTemp);
		}

//		temps.forEach(System.out::println);
//        temps.forEach(t -> t.episodes().forEach(e -> System.out.println(e.title())));

        var eps = temps.stream().flatMap(t -> t.episodes().stream()).collect(Collectors.toList());

        System.out.println("best five eps");
        eps.stream()
                .sorted(Comparator.comparing(DataEp::rating)
                .reversed())
                .filter(e -> !e.rating().equalsIgnoreCase("N/A"))
                .limit(5)
                .forEach(System.out::println);

        List<Episode> episodes = temps.stream()
                .flatMap(t -> t.episodes().stream()
                        .map(e -> new Episode(t.season(),e)))
                .collect(Collectors.toList());

        episodes.forEach(System.out::println);

        System.out.println("What do year you want see the episodes?");
        var year = sc.nextInt();
        sc.nextLine();

        LocalDate searchDate = LocalDate.of(year, 1, 1);

        System.out.println(searchDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        episodes.stream()
                .filter(Objects::nonNull)
                .filter(e -> e.getRelease().isAfter(searchDate))
                .forEach(e -> System.out.println(
                        String.format("Season:%d Episode:%s Date release:%s", e.getSeason(),e.getEp(),e.getRelease().format(formatter))
                ));



    }
}

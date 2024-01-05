package br.com.springProject.screenmatch.application;

import br.com.springProject.screenmatch.model.DataEp;
import br.com.springProject.screenmatch.model.DataSerie;
import br.com.springProject.screenmatch.model.DataTemp;
import br.com.springProject.screenmatch.service.ConvertData;
import br.com.springProject.screenmatch.service.GetApi;

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
    }
}
package br.com.persisteJpa.application;

import br.com.persisteJpa.entities.Episode;
import br.com.persisteJpa.entities.Serie;
import br.com.persisteJpa.model.records.DataEpisode;
import br.com.persisteJpa.model.records.DataSeason;
import br.com.persisteJpa.model.records.DataSerie;
import br.com.persisteJpa.repository.EpisodeRepository;
import br.com.persisteJpa.repository.SerieRepository;
import br.com.persisteJpa.utils.DataManager;
import br.com.persisteJpa.utils.IScanner;
import br.com.persisteJpa.utils.RequestManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class App implements IScanner{

    public static final String ADDRESS = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=6585022c";

    private SerieRepository serieRep;

    private EpisodeRepository episodeRep;

    private DataManager dataManager = new DataManager();

    private RequestManager requestManager = new RequestManager();

    private List<DataSerie> dataSeries = new ArrayList<>();
    private List<DataEpisode> dataEpisodes = new ArrayList<>();

    public App(SerieRepository serieRep, EpisodeRepository episodeRep) {

        this.serieRep = serieRep;
        this.episodeRep = episodeRep;

    }


    public void run(){
        showMenu();
    }
    public void showMenu() {
        var option = 0;

        do {
            var menu = """
                    1 - Find series
                    2 - Find episode
                    3 - List search series

                    0 - Exit
                    """;

            System.out.println(menu);
            option = scIn.nextInt();
            scIn.nextLine();

            switch (option) {
                case 1 -> searchWebSerie();
                case 2 -> searchEpForSerie();
                case 3 -> printList();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid option");
            }

        } while (option != 0);
    }

    private void searchWebSerie() {
        DataSerie data = getDataSerie();
        dataSeries.add(data);
        Serie serie = new Serie(data);
        serieRep.save(serie);
        System.out.println(data);
    }

    private DataSerie getDataSerie() {
        System.out.print("Write the series for to get: ");
        var nameSearch = scIn.nextLine();
        var json = requestManager.request(nameSearch.replace(" ", "+") + API_KEY);
        return dataManager.converter(json, DataSerie.class);
    }

    private void searchEpForSerie(){
        DataSerie dataSerie = getDataSerie();
        List<DataSeason> seasons = new ArrayList<>();

        for (int i = 1; i <= dataSerie.totalSeasons(); i++) {
            var json = requestManager.request(String.format("%s&season=%d%s", dataSerie.title().replace(" ", "+"), i, API_KEY));
            var dataSeason = dataManager.converter(json, DataSeason.class);
            seasons.add(dataSeason);
        }
    }

    private void printList(){
        List<Serie> series = serieRep.findAll();
        series
                .stream()
                .sorted(Comparator.comparing(Serie::getGenre))
                .forEach(System.out::println);
    }

}

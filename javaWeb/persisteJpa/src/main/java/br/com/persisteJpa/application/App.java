package br.com.persisteJpa.application;

import br.com.persisteJpa.entities.Episode;
import br.com.persisteJpa.entities.Serie;
import br.com.persisteJpa.model.enums.Categorie;
import br.com.persisteJpa.model.records.DataEpisode;
import br.com.persisteJpa.model.records.DataSeason;
import br.com.persisteJpa.model.records.DataSerie;
import br.com.persisteJpa.repository.EpisodeRepository;
import br.com.persisteJpa.repository.SerieRepository;
import br.com.persisteJpa.utils.DataManager;
import br.com.persisteJpa.utils.IScanner;
import br.com.persisteJpa.utils.RequestManager;
import org.springframework.cglib.core.Local;

import javax.sound.midi.Soundbank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class App implements IScanner{

    public static final String ADDRESS = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=6585022c";

    private SerieRepository serieRep;

    private EpisodeRepository episodeRep;

    private DataManager dataManager = new DataManager();

    private RequestManager requestManager = new RequestManager();

    private List<DataSerie> dataSeries = new ArrayList<>();
    private List<DataEpisode> dataEpisodes = new ArrayList<>();

    private List<Serie> seasons = new ArrayList<>();

    private Optional<Serie> serieFind;

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
                    1 - Find seasons
                    2 - Find episode
                    3 - List search series
                    4 - Find serie for title
                    5 - Find serie for actor
                    6 - Find Best five serie
                    7 - Find by categorie
                    8 - Find by total season
                    9 - Find episode by stretch
                    10 - Find Best five episode
                    11 - Find episodes by date
                    
                    0 - Exit
                    """;

            System.out.println(menu);
            option = scIn.nextInt();
            scIn.nextLine();

            switch (option) {
                case 1 -> searchWebSerie();
                case 2 -> searchEpForSerie();
                case 3 -> printList();
                case 4 -> findByTitle();
                case 5 -> findByActor();
                case 6 -> findByBestFiveSerie();
                case 7 -> findByGenre();
                case 8 -> findBySeason();
                case 9 -> findEpByStretch();
                case 10 -> findTopEpisode();
                case 11 -> findEpByDate();
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
        printList();
        System.out.print("Write the name serie: ");
        var name = scIn.nextLine();
        var request = serieRep.findByTitleContainingIgnoreCase(name);

        if(request.isPresent()) {
            var getSeason = request.get();
            List<DataSeason> season = new ArrayList<>();

            for (int i = 1; i <= getSeason.getTotalSeasons(); i++) {
                var json = requestManager.request(String.format("%s&season=%d%s", getSeason.getTitle().replace(" ", "+"), i, API_KEY));
                var dataSeason = dataManager.converter(json, DataSeason.class);
                season.add(dataSeason);
            }

            season.forEach(System.out::println);

            var episodes = season.stream()
                    .flatMap(s -> s.episodes()
                            .stream().map(e -> new Episode(s.number(), e)))
                    .collect(Collectors.toList());

            getSeason.setEpisodes(episodes);
            serieRep.save(getSeason);

        } else System.out.println("not find");

    }

    private void printList(){
        seasons = serieRep.findAll();
        seasons
                .stream()
                .sorted(Comparator.comparing(Serie::getGenre))
                .forEach(System.out::println);
    }

    private void findByTitle() {
        System.out.print("Write the title serie: ");
        var name = scIn.nextLine();

        serieFind = serieRep.findByTitleContainingIgnoreCase(name);

        if(serieFind.isPresent()) System.out.println("Data: " + serieFind.get());
        else System.out.println("Not find season");

    }

    private void findByActor() {
        System.out.print("What name for search:");
        var name = scIn.nextLine();
        System.out.print("Rating a from what value:");
        var rating = scIn.nextDouble();
        List<Serie> getSeason = serieRep.findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(name, rating);
        System.out.println("Series that this actor worked");
        getSeason.forEach(s -> System.out.println(String.format("%s, rating: %s", s.getTitle(), s.getRating())));
    }

    private void findByBestFiveSerie() {

        List<Serie> bestSerie = serieRep.findTop5ByOrderByRatingDesc();
        System.out.println("Series that this actor worked");
        bestSerie.forEach(s -> System.out.println(String.format("%s, rating: %s", s.getTitle(), s.getRating())));

    }

    private void findByGenre() {
        System.out.println("What genre do want you?");
        var nameGenre = scIn.nextLine();
        Categorie categorie = Categorie.parseString(nameGenre);
        List<Serie> serieByGenre = serieRep.findByGenre(categorie);
        System.out.println("Serie by genre: ");

        serieByGenre.forEach(System.out::println);
    }

    private void findBySeason() {
        System.out.println("Write the total season you want to watch?");
        var numberSeason = scIn.nextInt();
        System.out.println("Write the rating of serie: ");
        var ratingSeason = scIn.nextDouble();

        List<Serie> perSeason = serieRep.findByTotalSeasons(numberSeason, ratingSeason);

        System.out.println("------------");
        perSeason.forEach(System.out::println);

    }

    private void findEpByStretch() {
        System.out.print("Write the name episode to find: ");
        var stretchEpisode =  scIn.nextLine();
        var test = serieRep.findEpByStretch(stretchEpisode);
        test.forEach(e ->
                System.out.printf("Serie: %s Season: %s - Episode %s - %s%n",
                        e.getSerie().getTitle(),
                        e.getSeason(),
                        e.getNumberEpisode(),
                        e.getTitle()));
    }

    private void findTopEpisode() {
        findByTitle();

        if(serieFind.isPresent()) {
            var getSerie = serieFind.get();
            List<Episode> topEpisodes = serieRep.findTop5Episode(getSerie);
            topEpisodes.forEach(e -> System.out.printf("Serie: %s Season: %s - Episode %s - Title %s - Rating: %s%n", e.getSerie().getTitle(), e.getSeason(), e.getNumberEpisode(), e.getTitle(), e.getRating()));
        }
    }

    private void findEpByDate() {
        findByTitle();

        if(serieFind.isPresent()){
            var serie = serieFind.get();

            System.out.print("Write the limit year want:");
            var date = scIn.nextInt();
            scIn.nextLine();

            List<Episode> dateEpisodes = serieRep.findEpByDate(serie, date);
            dateEpisodes.forEach(System.out::println);
        }

    }


}

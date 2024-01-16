package br.com.finderMusic.utils;

import br.com.finderMusic.dto.Docs;
import br.com.finderMusic.dto.MusicDto;
import br.com.finderMusic.dto.ResponseDto;
import br.com.finderMusic.entity.Artist;
import br.com.finderMusic.entity.Music;
import br.com.finderMusic.enums.TypeRequest;
import br.com.finderMusic.repository.ArtistRep;
import br.com.finderMusic.services.RequestManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

//this just to print

@Service
@Configurable
public class Menu implements IScanner {
    public static boolean on = true;

    @Autowired
    private ArtistRep rep;

    private final RequestManager requestManager = new RequestManager();

    private final DataManager dataManager = new DataManager();

    public void menuHome(){
        System.out.printf("""
                1- Register artist
                2- Register music
                3- Listing music
                4- Find music by artist
                5- search data about the artist
                
                0- Exit
                """);

        var option = scIn.nextInt();
        scIn.nextLine();
        menuOption(option);
    }


    public void menuOption(Integer number){
        switch (number){
            case 1 -> registerArtist();
            case 2 -> registerMusic();
            case 3 -> ListingMusic();
            case 4 -> findMusicByArtist();
            case 5 -> findAboutArtist();

            case 0 -> closeApplication();
        }
    }

    private void registerArtist() {
        System.out.print("Write the name to your artist: ");
        var name = scIn.nextLine();
        var response = rep.save(new Artist(name));
        System.out.println(response);
    }

    private void registerMusic() {
        System.out.print("Write the music name: ");
        var music = scIn.nextLine();

        System.out.print("Write the artist: ");
        var artist = scIn.nextLine();

        var strSend = String.format("%s?q=%s %s", TypeRequest.ARTMUS.toString().toLowerCase(), artist, music);

        System.out.println(strSend);

        var json = requestManager.toRequest(strSend);

        Docs docs  = dataManager.converter(json, ResponseDto.class).response();


        System.out.println(artist.equalsIgnoreCase("Tony Allysson"));
        var musics = docs.musicDtos().stream()
                .filter(m -> m.title() != null)
                .filter(m -> m.artist() != null)
                .map(Music::new)
                .toList();

        System.out.print("Select one, which: \n");
        for(int i = 0; i < musics.size(); i++)
            System.out.printf("%s- %s by %s%n", (i+1), musics.get(i).getTitle(), musics.get(i).getArtist().getName());

        var option = scIn.nextInt()-1;
        var artistResponse = musics.get(option).getArtist();
        artistResponse.getMusics().add(musics.get(option));

        rep.save(artistResponse);
    }

    private void ListingMusic() {
        System.out.println("");
        var response = scIn.nextLine();
    }

    private void findMusicByArtist() {
        System.out.print("Write the artist name: ");
        var response = scIn.nextLine();
        rep.findMusicByArtistId(response);
    }

    private void findAboutArtist() {
        System.out.println("");
        var response = scIn.nextLine();
    }
    private void closeApplication() {
        System.out.println("Exiting...");
        on = false;
    }

}

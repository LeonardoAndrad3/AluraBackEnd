package br.com.finderMusic.utils;

import br.com.finderMusic.application.App;
import br.com.finderMusic.dto.ArtistDto;
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

    private String strSend, json;

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

        strSend = RequestManager.formatString(App.ADDRESS_ARTIST, "-", name);

        System.out.println(strSend);

        json = requestManager.toRequest(strSend);

        var dataArtist = dataManager.formatJson(json, "/desc", "/toplyrics/item", "/pic_medium" );

        ArtistDto artistDto = dataManager.converter(dataArtist, ArtistDto.class);

        System.out.println(artistDto);

        var response = rep.save(new Artist(artistDto));

        System.out.println(response);
    }

    private void registerMusic() {
        System.out.print("Write the music name: ");
        var music = scIn.nextLine();

        System.out.print("Write the artist: ");
        var name = scIn.nextLine();

        var opt = rep.findByNameContainingIgnoreCase(name);

        
        if(opt.isPresent()){
            opt.get();
        } else{
            System.out.print("Artist not found, please, register: ");
            registerArtist();
            opt = rep.findByNameContainingIgnoreCase(name);
        }

        strSend = RequestManager.formatString(App.ADDRESS+".%s?q=%s %s&limit=1","%20", TypeRequest.ARTMUS.toString().toLowerCase(), name, music);

        json = requestManager.toRequest(strSend);
        json = dataManager.getFildName(json, "/response/docs");

        var musicResponse = dataManager.converterList(json, MusicDto.class).stream().filter(m -> m.title() != null).findFirst().map(m -> new Music(m, artist)).orElseGet(null);

        if(music != null){
            artist.getMusics().add(musicResponse);
            rep.save(artist);
        } else
            System.out.println("Music not found");
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

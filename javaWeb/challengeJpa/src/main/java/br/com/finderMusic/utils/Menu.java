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
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.dao.DataIntegrityViolationException;
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

    Artist artist;

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
            case 6 -> removeAristAndMusics();

            case 0 -> closeApplication();
        }
    }


    private void registerArtist() {
        System.out.print("Write the name to your artist: ");
        var name = scIn.nextLine();

        strSend = RequestManager.formatString(App.ADDRESS_ARTIST, "-", name);
        json = requestManager.toRequest(strSend);
        if(DataManager.error(json)) return;

        var dataArtist = dataManager.formatJson(json, "/desc", "/toplyrics/item", "/pic_medium" );

        System.out.println(dataArtist);

        ArtistDto artistDto = dataManager.converter(dataArtist, ArtistDto.class);

        try {
            this.artist = rep.save(new Artist(artistDto));
            System.out.println(artist);

        } catch (DataIntegrityViolationException e ){
            System.out.println(e.getMessage()+"\nPlease, try again");
        }
    }

    private void registerMusic() {

        System.out.print("Write the music name: ");
        var music = scIn.nextLine();

        registerArtist();

        var opt = rep.findById(artist.getId());

        if(opt.isPresent()){
            opt.get();
        } else{
            System.out.print("Artist not found, please, register: ");
            return;
        }

        strSend = RequestManager.formatString(App.ADDRESS+".%s?q=%s %s&limit=1","%20", TypeRequest.ARTMUS.toString().toLowerCase(), this.artist.getName(), music);

        json = requestManager.toRequest(strSend);

        System.out.println(json.charAt(0));

        json = dataManager.formatJson(json, "/response/docs");

        var musicResponse = dataManager.converterList(json, MusicDto.class).stream().filter(m -> m.title() != null).findFirst().map(m -> new Music(m, artist)).orElseGet(null);

        if(music != null){
            artist.getMusics().add(musicResponse);
            rep.save(artist);
        } else
            System.out.println("Music not found");
    }

    private void ListingMusic() {
        var musics = rep.findAllMusic();
        musics.forEach(System.out::println);
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

    private void removeAristAndMusics() {
        System.out.print("Write the name artist: ");
        var name = scIn.nextLine();
        rep.removeArtist(name);
    }


}

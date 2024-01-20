package br.com.finderMusic.utils;

import br.com.finderMusic.application.App;
import br.com.finderMusic.dto.ArtistDto;
import br.com.finderMusic.dto.MusicDto;
import br.com.finderMusic.entity.Artist;
import br.com.finderMusic.entity.Music;
import br.com.finderMusic.enums.TypeRequest;
import br.com.finderMusic.repository.ArtistRep;
import br.com.finderMusic.services.RequestManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

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
                5- Remove artist and yours music
                
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
            case 5 -> removeAristAndMusics();

            case 0 -> closeApplication();
        }
    }

    private void registerArtist() {
        System.out.print("Write the name to your artist: ");
        var name = scIn.nextLine();

        if(rep.exist(name)){
            rep.findByNameContainingIgnoreCase(name).ifPresent(a -> this.artist = a);
            System.out.println("Artist has register");
            return;
        }

        strSend = RequestManager.formatString(App.ADDRESS_ARTIST, "-", name);
        json = requestManager.toRequest(strSend);

        if(DataManager.error(json)) return;

        var dataArtist = dataManager.formatJson(json, "/desc", "/toplyrics/item", "/pic_medium" );
        ArtistDto artistDto = dataManager.converter(dataArtist, ArtistDto.class);

        try {
            this.artist = rep.save(new Artist(artistDto));
            System.out.println("Artist registered");
        } catch (RuntimeException e ){
            System.out.println("{Err: "+e.getMessage()+"}");
        }
    }

    private void registerMusic() {

        System.out.print("Write the music name: ");
        var music = scIn.nextLine();

        registerArtist();

        var opt = rep.findById(artist.getId());

        strSend = RequestManager.formatString(
                App.ADDRESS+".%s?q=%s %s&limit=1",
                "%20",
                TypeRequest.ARTMUS.toString().toLowerCase(),
                this.artist.getName(),
                music);

        json = requestManager.toRequest(strSend);
        json = dataManager.formatJson(json, "/response/docs");

        var musicResponse = dataManager.converterList(json, MusicDto.class).stream().filter(m -> m.title() != null).findFirst().map(m -> new Music(m, artist)).orElseGet(null);

        if(musicResponse != null){
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
        var musics = rep.findMusicByArtist(response);

        musics.forEach(System.out::println);
    }

    private void closeApplication() {
        System.out.println("Exiting...");
        on = false;
        scIn.close();
    }

    private void removeAristAndMusics() {
        System.out.print("Write the name artist: ");
        var name = scIn.nextLine();
        rep.removeArtist(name);
        System.out.println("Complete removing");
    }
}

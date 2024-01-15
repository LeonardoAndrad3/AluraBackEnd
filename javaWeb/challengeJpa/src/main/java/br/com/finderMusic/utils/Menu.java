package br.com.finderMusic.utils;

import br.com.finderMusic.entity.Artist;
import br.com.finderMusic.repository.ArtistRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//this just to print

@Controller
public class Menu implements IScanner {
    public static boolean on = true;

    @Autowired
    private ArtistRep rep;

    @Autowired
    public Menu(ArtistRep rep){
        this.rep = rep;
    }

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
        System.out.println("");
        var response = scIn.nextLine();
    }

    private void ListingMusic() {
        System.out.println("");
        var response = scIn.nextLine();
    }

    private void findMusicByArtist() {
        System.out.println("");
        var response = scIn.nextLine();
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

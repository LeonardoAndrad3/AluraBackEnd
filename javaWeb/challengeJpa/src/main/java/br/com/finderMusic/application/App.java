package br.com.finderMusic.application;

import br.com.finderMusic.services.ArtistService;
import br.com.finderMusic.utils.IScanner;
import br.com.finderMusic.utils.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class App implements IScanner {

    public static final String ADDRESS = "https://api.vagalume.com.br/search";
    public static final String API_KEY = "apikey=660a4395f992ff67786584e238f501aa";

    @Autowired
    private ArtistService service;

    @Autowired
    private Menu menu;

    public void run() {
        while(Menu.on){
            menu.menuHome();
        }
    }
}

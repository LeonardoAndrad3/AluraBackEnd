package br.com.finderMusic.application;

import br.com.finderMusic.services.ArtistService;
import br.com.finderMusic.utils.IScanner;
import br.com.finderMusic.utils.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class App implements IScanner {

    @Autowired
    private ArtistService service;
    @Autowired
    private Menu menu;

    public void run() {

        while (Menu.on){
            menu.menuHome();
        }

    }

}

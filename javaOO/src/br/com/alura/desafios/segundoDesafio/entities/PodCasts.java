package br.com.alura.desafios.segundoDesafio.entities;

import br.com.alura.desafios.segundoDesafio.resource.Audio;
import br.com.alura.desafios.segundoDesafio.resource.Classification;

public class PodCasts extends Audio implements Classification {

    private String presenter;
    private String guest;
    private String theme;

    public PodCasts(String title, int duration, int totalOfReproduction, int likes, int classification, String presenter, String guest, String theme) {
        super(title, duration, totalOfReproduction, likes, classification);
        this.presenter = presenter;
        this.guest = guest;
        this.theme = theme;
    }

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public double getClassificationFilter() {
        return ((double) likes/totalOfReproduction);
    }
}

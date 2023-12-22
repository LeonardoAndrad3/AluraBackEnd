package br.com.alura.desafios.segundoDesafio.entities;

import br.com.alura.desafios.segundoDesafio.resource.Audio;
import br.com.alura.desafios.segundoDesafio.resource.Classification;

public class Music extends Audio implements Classification {

    private String artist;
    private String style;

    public Music(String title, int duration, int totalOfReproduction, int likes, int classification, String artist, String style) {
        super(title, duration, totalOfReproduction, likes, classification);
        this.artist = artist;
        this.style = style;
    }

    public Music() {
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public double getClassificationFilter() {
        return ((double) likes/totalOfReproduction);
    }
}

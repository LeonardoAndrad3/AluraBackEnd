package br.com.alura.aula.resource;

import br.com.alura.aula.resource.math.Classification;

public class Film extends Title implements Classification {

    private String autor;

    public Film(String name, int releaseYear, boolean plane, double rating, int totalRating, int durationInMinutes, String autor) {
        super(name, releaseYear, plane, rating, totalRating, durationInMinutes);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public int getClassification(){
        return (int) getRating();
    }
    
    @Override
    public String toString() {
        return "Filme: " + this.getName() + "{"+this.getReleaseYear()+"}";
    }
}

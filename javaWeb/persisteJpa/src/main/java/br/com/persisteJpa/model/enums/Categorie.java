package br.com.persisteJpa.model.enums;

import java.util.Arrays;

public enum Categorie {

    ACTION("Action"),
    ROMANCE("Romance"),
    CRIME("Crime"),
    DRAMA("Drama"),
    COMEDY("Comedy");

    private String categorieOmdb;

    Categorie(String categorieOmdb){
        this.categorieOmdb = categorieOmdb;
    }

    public static Categorie parseString(String genre){
       var cat = Arrays.stream(Categorie.values()).filter(c -> c.categorieOmdb.equalsIgnoreCase(genre)).findFirst();

       return cat.orElseThrow(() -> new IllegalArgumentException("Cat not find"));
    }
}

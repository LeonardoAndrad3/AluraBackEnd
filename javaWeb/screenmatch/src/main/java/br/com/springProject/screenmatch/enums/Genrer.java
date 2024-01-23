package br.com.springProject.screenmatch.enums;

import javax.swing.*;
import java.util.Arrays;
import java.util.Comparator;

public enum Genrer {

    ACTION("action"),
    ROMANCE("romance"),
    CRIME("crime"),
    COMEDY("comedy"),
    DRAMA("drama");

    private String omdbString;

    Genrer(String string){
        this.omdbString = string;
    }

    public static Genrer parse(String str){
        var result = Arrays.stream(Genrer.values()).filter(g -> g.omdbString.equalsIgnoreCase(str)).findFirst();
        return result.orElseThrow(() -> new RuntimeException("not convert this"));
    }
}

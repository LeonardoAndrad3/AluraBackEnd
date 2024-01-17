package br.com.finderMusic.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Career {

    SINGLE("solo"),
    DUO("duo"),
    BAND("band");

    private final String career;
    Career(String str) {
        this.career = str;
    }

    public static Career parseString(String str){
        Optional<Career> opt = Arrays.stream(Career.values()).filter( c -> c.career.equalsIgnoreCase(str)).findFirst();

        return opt.orElseThrow(()-> new RuntimeException("It's not possible to convert"));
    }

}

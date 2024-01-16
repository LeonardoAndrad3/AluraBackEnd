package br.com.finderMusic.enums;

public enum TypeRequest {

    ART("art"),
    EXCERPT("excerpt"),
    ARTMUS("artmus"),
    ALB("alb");

    private String vagalume;

    TypeRequest(String vagalume) {
        this.vagalume = vagalume;
    }


}

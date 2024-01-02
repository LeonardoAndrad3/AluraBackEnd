package br.com.alura.aula.resource;

import br.com.alura.exceptions.ErrorConvertYearException;
import com.google.gson.annotations.SerializedName;

public class Title implements Comparable<Title>{


    private String name;
    private int releaseYear;
    private boolean plane;
    private double rating;
    private int totalRating;
    private int durationInMinutes;

    public Title(String name, int releaseYear, boolean plane, double rating, int totalRating, int durationInMinutes) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.plane = plane;
        this.rating = rating;
        this.totalRating = totalRating;
        this.durationInMinutes = durationInMinutes;
    }

      public Title(String name, int releaseYear) {
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public Title() {
    }

    public Title(TitleOmdb titleOmdb) {
        this.name = titleOmdb.title();

        if(titleOmdb.year().length() > 4)
            throw new ErrorConvertYearException("cannot convert the year, because it has more than four characters");

        this.releaseYear = Integer.valueOf(titleOmdb.year());
        this.durationInMinutes = Integer.valueOf(titleOmdb.runtime().substring(0, titleOmdb.runtime().indexOf(" ")));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public boolean isPlane() {
        return plane;
    }

    public void setPlane(boolean plane) {
        this.plane = plane;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getTotalRating(){
        return totalRating;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void getTechnicRecord(){
        System.out.println("Name: " + name);
        System.out.println("Relase: "+ releaseYear);
    }

    public void rate(double nota){
        rating = (rating*totalRating)+nota;
        totalRating++;
        ratingAverage();
    }

    private void ratingAverage(){
        rating /=totalRating;
    }

    @Override
    public int compareTo(Title other) {
        return this.getName().compareTo(other.getName());
    }


    @Override
    public String toString() {
        return
                "(name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", durationInMinutes=" + durationInMinutes +
                ')';
    }
}

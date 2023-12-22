package br.com.alura.aula.resource;

public abstract class Title {

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

    public Title() {
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

}

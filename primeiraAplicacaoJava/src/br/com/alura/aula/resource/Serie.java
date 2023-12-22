package br.com.alura.aula.resource;

public class Serie extends Title{

    private int temp;
    private boolean turn;
    private int epPerTemp;
    private int epMinites;

    public Serie(String name, int releaseYear, boolean plane, double rating, int totalRating, int durationInMinutes) {
        super(name, releaseYear, plane, rating, totalRating, durationInMinutes);
    }

    public Serie(String name, int releaseYear, boolean plane, double rating, int totalRating, int durationInMinutes, int temp, boolean turn, int epPerTemp, int epMinites) {
        super(name, releaseYear, plane, rating, totalRating, durationInMinutes);
        this.temp = temp;
        this.turn = turn;
        this.epPerTemp = epPerTemp;
        this.epMinites = epMinites;

        super.setDurationInMinutes(temp*epPerTemp*epMinites);
    }

    public Serie() {
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int getEpPerTemp() {
        return epPerTemp;
    }

    public void setEpPerTemp(int epPerTemp) {
        this.epPerTemp = epPerTemp;
    }

    public int getEpMinites() {
        return epMinites;
    }

    public void setEpMinites(int epMinites) {
        this.epMinites = epMinites;
    }

    @Override
    public int getDurationInMinutes() {
        return super.getDurationInMinutes();
    }


}

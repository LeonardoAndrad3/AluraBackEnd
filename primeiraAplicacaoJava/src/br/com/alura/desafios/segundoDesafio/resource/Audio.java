package br.com.alura.desafios.segundoDesafio.resource;

public abstract class Audio {

    protected String title;
    protected int duration;
    protected int totalOfReproduction;
    protected int likes;
    protected int classification;

    public Audio(String title, int duration, int totalOfReproduction, int likes, int classification) {
        this.title = title;
        this.duration = duration;
        this.totalOfReproduction = totalOfReproduction;
        this.likes = likes;
        this.classification = classification;
    }

    public Audio() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTotalOfReproduction() {
        return totalOfReproduction;
    }

    public void setTotalOfReproduction(int totalOfReproduction) {
        this.totalOfReproduction = totalOfReproduction;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }
}

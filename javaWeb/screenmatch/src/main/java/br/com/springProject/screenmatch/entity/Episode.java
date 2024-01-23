package br.com.springProject.screenmatch.entity;

import br.com.springProject.screenmatch.model.DataEp;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer season;
    private String title;
    private Integer ep;
    private double rating;
    private LocalDate release;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Serie serie;

    public Episode(Integer season, DataEp data){
        this.season = season;
        this.title = data.title();
        this.ep = data.ep();

        try {
            this.rating = Double.valueOf(data.rating());
        } catch (NumberFormatException ignored){

        }

        try {
            this.release = LocalDate.parse(data.release());
        }catch (DateTimeParseException ignored){

        }
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEp() {
        return ep;
    }

    public void setEp(Integer ep) {
        this.ep = ep;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return
                "season=" + season +
                ", title='" + title + '\'' +
                ", ep=" + ep +
                ", rating=" + rating +
                ", release=" + release;
    }
}

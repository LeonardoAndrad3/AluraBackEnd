package br.com.persisteJpa.entities;

import br.com.persisteJpa.model.records.DataEpisode;
import jakarta.persistence.*;
import org.hibernate.annotations.ManyToAny;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer season;

    @Column(unique = true)
    private String title;
    private Integer numberEpisode;
    private Double rating;
    private LocalDate release;

    @ManyToOne
    private Serie serie;



    public Episode(Integer numberEpisode, DataEpisode dataEpisode) {
        this.season = numberEpisode;
        this.title = dataEpisode.title();
        this.numberEpisode = dataEpisode.number();

        try {
            this.rating = Double.valueOf(dataEpisode.rating());
        } catch (NumberFormatException ex) {
            this.rating = 0.0;
        }

        try {
            this.release = LocalDate.parse(dataEpisode.release());
        } catch (DateTimeParseException ex) {
            this.release = null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
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

    public Integer getNumberEpisode() {
        return numberEpisode;
    }

    public void setNumberEpisode(Integer numberEpisode) {
        this.numberEpisode = numberEpisode;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    @Override
    public String toString() {
        return "Season=" + season +
                ", title='" + title + '\'' +
                ", numberEpisode=" + numberEpisode +
                ", rating=" + rating +
                ", release=" + release ;
    }

}

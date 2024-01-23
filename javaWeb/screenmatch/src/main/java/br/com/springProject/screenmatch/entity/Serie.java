package br.com.springProject.screenmatch.entity;


import br.com.springProject.screenmatch.enums.Genrer;
import br.com.springProject.screenmatch.model.DataSerie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Serie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer seasons;
    private String rating;

    @Enumerated(EnumType.STRING)
    private Genrer gener;

    private String atores;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episode> episodes;

    public Serie() {
    }

    public Serie(DataSerie data) {
        this.title = data.title();
        this.seasons = data.seasons();
        this.rating = data.rating();
    }

    public Serie(Long id, String title, Integer seasons, String rating, List<Episode> episodes) {
        this.id = id;
        this.title = title;
        this.seasons = seasons;
        this.rating = rating;
        this.episodes = episodes;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSeasons() {
        return seasons;
    }

    public void setSeasons(Integer seasons) {
        this.seasons = seasons;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", seasons=" + seasons +
                ", rating='" + rating + '\'' +
                '}';
    }
}

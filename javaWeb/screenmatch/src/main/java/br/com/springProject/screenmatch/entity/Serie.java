package br.com.springProject.screenmatch.entity;


import br.com.springProject.screenmatch.model.DataSerie;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private Integer seasons;
    private String rating;

    public Serie() {
    }

    public Serie(DataSerie data) {
        this.title = data.title();
        this.seasons = data.seasons();
        this.rating = data.rating();
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

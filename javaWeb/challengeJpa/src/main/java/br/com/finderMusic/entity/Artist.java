package br.com.finderMusic.entity;

import br.com.finderMusic.dto.ArtistDto;
import br.com.finderMusic.enums.Career;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Artist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    private String photo;

    @Enumerated(EnumType.STRING)
    private Career career;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Music> musics = new ArrayList<>();

    public Artist(ArtistDto dto) {
        this.name = dto.name();
        this.musics = dto.toplyrics().stream().map(m -> new Music(m, this)).collect(Collectors.toList());
        this.photo = dto.photo();
    }

    public Artist() {

    }

    public Artist(String name) {
        this.name = name;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Music> getMusics() {
        return musics;
    }

    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", musics=" + musics +
                '}';
    }
}

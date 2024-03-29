package br.com.finderMusic.entity;


import br.com.finderMusic.dto.MusicDto;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;

import javax.swing.text.html.Option;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Music implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    private String album;

    @ManyToOne
    private Artist artist;

    public Music(Long id, String title, String album, Artist artist) {
        this.id = id;
        this.title = title;
        this.album = album;
        this.artist = artist;
    }

    public Music(MusicDto dto, Artist artist) {
        if(dto.title() != null)
            this.title = dto.title();
        else
            this.title = "none";

            this.artist = artist;
    }

    public Music() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", artist=" + artist.getName() +
                '}';
    }
}

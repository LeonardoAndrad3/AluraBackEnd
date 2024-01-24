package br.com.challengeApiFilms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "frases")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo",unique = true)
    private String title;
    @Column(name = "frase")
    private String word;
    @Column(name = "personagem")
    private String person;
    @Column(name = "poster")
    private String poster;

    public Film() {
    }

    public Film(String title, String word, String person, String poster) {
        this.title = title;
        this.word = word;
        this.person = person;
        this.poster = poster;
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

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", word='" + word + '\'' +
                ", person='" + person + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}

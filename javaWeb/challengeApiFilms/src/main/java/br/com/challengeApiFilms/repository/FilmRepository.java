package br.com.challengeApiFilms.repository;

import br.com.challengeApiFilms.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("SELECT f FROM Film f order by function('RANDOM') LIMIT 1")
    Film findRandomFilm();
}

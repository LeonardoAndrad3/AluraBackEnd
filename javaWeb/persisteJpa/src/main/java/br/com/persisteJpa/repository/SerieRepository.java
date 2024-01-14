package br.com.persisteJpa.repository;

import br.com.persisteJpa.entities.Episode;
import br.com.persisteJpa.entities.Serie;
import br.com.persisteJpa.model.enums.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {

    Optional<Serie> findByTitleContainingIgnoreCase(String nameSeason);
    List<Serie> findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(String name, Double rating);
    List<Serie> findTop5ByOrderByRatingDesc();
    List<Serie> findByGenre(Categorie categorie);
//    List<Serie> findByTotalSeasonsLessThanEqualAndRatingGreaterThanEqual(Integer totalSeason, Double rating);
    @Query("select s from Serie s where s.totalSeasons <= :totalSeason and s.rating >= :rating")
    List<Serie> findByTotalSeasons(Integer totalSeason, Double rating);

    @Query("select e from Serie s inner join s.episodes e on e.title ilike %:stretchEpisode%")
    List<Episode> findEpByStretch(String stretchEpisode);

    @Query("SELECT e FROM Serie s inner join s.episodes e ON s = :serie ORDER BY e.rating DESC LIMIT 5")
    List<Episode> findTop5Episode(Serie serie);

    @Query("SELECT e FROM Serie s inner join s.episodes e on s = :serie where YEAR(e.release) >= :date")
    List<Episode> findEpByDate(Serie serie, Integer date);
}

package br.com.persisteJpa.repository;

import br.com.persisteJpa.entities.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {

    Optional<Serie> findByTitleContainingIgnoreCase(String nameSeason);
    List<Serie> findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(String name, Double rating);
    List<Serie> findTop5ByOrderByRatingDesc();


}

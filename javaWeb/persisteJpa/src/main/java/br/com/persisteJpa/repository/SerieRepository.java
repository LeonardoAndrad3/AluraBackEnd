package br.com.persisteJpa.repository;

import br.com.persisteJpa.entities.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {

}

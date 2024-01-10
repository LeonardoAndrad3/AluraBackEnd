package br.com.persisteJpa.repository;

import br.com.persisteJpa.entities.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Serie, Long> {

}

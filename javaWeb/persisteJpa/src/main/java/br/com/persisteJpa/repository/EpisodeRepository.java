package br.com.persisteJpa.repository;

import br.com.persisteJpa.entities.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Serie, Long> {

}

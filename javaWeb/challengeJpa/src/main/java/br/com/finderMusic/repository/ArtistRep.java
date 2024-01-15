package br.com.finderMusic.repository;

import br.com.finderMusic.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRep extends JpaRepository<Artist, Long> {
    Optional<Artist> findArtistByName(String name);

}

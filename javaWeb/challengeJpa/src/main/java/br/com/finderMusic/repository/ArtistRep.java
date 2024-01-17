package br.com.finderMusic.repository;

import br.com.finderMusic.entity.Artist;
import br.com.finderMusic.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRep extends JpaRepository<Artist, Long> {

    @Query("SELECT m FROM Artist a inner join a.musics m on m.artist.id = a.id")
    List<Music> findMusicByArtistId(String name);

    Optional<Artist> findByNameContainingIgnoreCase(String name);

}

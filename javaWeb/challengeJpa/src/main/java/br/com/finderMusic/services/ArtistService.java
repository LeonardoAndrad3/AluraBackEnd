package br.com.finderMusic.services;

import br.com.finderMusic.entity.Artist;
import br.com.finderMusic.entity.Music;
import br.com.finderMusic.repository.ArtistRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService{

    @Autowired
    private ArtistRep rep;

    public Artist save(Artist artist){
        return rep.save(artist);
    }

    public Artist findById(Long id){
        Optional<Artist> opt = rep.findById(id);

        return opt.orElseThrow(() -> new RuntimeException("Not find this id"));
    }

    public List<Music> fundMusicByArtist(String name){
        return rep.findMusicByArtist(name);
    }
}

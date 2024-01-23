package br.com.persisteJpa.service;

import br.com.persisteJpa.dto.EpisodeDTO;
import br.com.persisteJpa.dto.SerieDTO;
import br.com.persisteJpa.entities.Episode;
import br.com.persisteJpa.entities.Serie;
import br.com.persisteJpa.model.enums.Categorie;
import br.com.persisteJpa.repository.SerieRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository rep;

    public Serie save(Serie s){
        return rep.save(s);
    }

    public SerieDTO findById(Long id){
        var opt = rep.findById(id);
        return opt.map(this::convert).orElseThrow(() -> new NullPointerException("Not find serie"));
    }

    public List<SerieDTO> findAll() {
        return rep.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    private SerieDTO convert(Serie s){
        return new SerieDTO(
                s.getId(),
                s.getTitle(),
                s.getTotalSeasons(),
                s.getRating(),
                s.getGenre(),
                s.getActors(),
                s.getPoster(),
                s.getPlot());
    }

    private EpisodeDTO convert(Episode e){
        return new EpisodeDTO(
                e.getTitle(),
                e.getNumberEpisode(),
                e.getSeason()
        );
    }

    public List<SerieDTO> topFive(){
        return rep.findTop5ByOrderByRatingDesc().stream().map(this::convert).collect(Collectors.toList());
    }

    public List<SerieDTO> release(){
        return rep.findTop5().stream().map(this::convert).collect(Collectors.toList());
    }

    public List<EpisodeDTO> findAllSeasons(Long id) {
        return rep.findById(id).map(s -> s.getEpisodes().stream().map(this::convert).collect(Collectors.toList())).orElseThrow(() -> new NullPointerException("Not found episodes"));
    }

    public List<EpisodeDTO> findSeasonById(Long id, Long season) {
        return rep.findSeasonBySerie(id, season).stream().map(this::convert).collect(Collectors.toList());
    }

    public List<SerieDTO> findByGenre(String categorie) {
        return rep.findByGenre(Categorie.parseString(categorie)).stream().map(this::convert).collect(Collectors.toList());
    }
}
















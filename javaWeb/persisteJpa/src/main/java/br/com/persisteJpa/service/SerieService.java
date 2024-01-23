package br.com.persisteJpa.service;

import br.com.persisteJpa.dto.SerieDTO;
import br.com.persisteJpa.entities.Serie;
import br.com.persisteJpa.repository.SerieRepository;
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

        return opt.map(
                this::convert)
                .orElseThrow(() -> new NullPointerException("Not find serie"));
    }

    public List<SerieDTO> findAll() {
        var series = rep.findAll().stream().map(this::convert).collect(Collectors.toList());
        return series;
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

}

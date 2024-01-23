package br.com.persisteJpa.controller;

import br.com.persisteJpa.application.App;
import br.com.persisteJpa.dto.EpisodeDTO;
import br.com.persisteJpa.dto.SerieDTO;
import br.com.persisteJpa.entities.Episode;
import br.com.persisteJpa.entities.Serie;
import br.com.persisteJpa.repository.SerieRepository;
import br.com.persisteJpa.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping
    public ResponseEntity<List<SerieDTO>> findAll(){
        var series = service.findAll();
        return ResponseEntity.ok().body(series);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieDTO> findById(@PathVariable Long id){
        var serie = service.findById(id);
        return ResponseEntity.ok().body(serie);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Serie json){
        var serie = service.save(json);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(serie.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/top5")
    public ResponseEntity<List<SerieDTO>> topFive(){
        var series = service.topFive();
        return ResponseEntity.ok().body(series);
    }

    @GetMapping("/release")
    public ResponseEntity<List<SerieDTO>> release(){
        var series = service.release();
        return ResponseEntity.ok().body(series);
    }

    @GetMapping("/{id}/seasons/all")
    public ResponseEntity<List<EpisodeDTO>> getAllSeasons(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findAllSeasons(id));
    }

    @GetMapping("/{id}/seasons/{season}")
    public ResponseEntity<List<EpisodeDTO>> getAllSeasons(@PathVariable Long id, @PathVariable Long season){
        return ResponseEntity.ok().body(service.findSeasonById(id, season));
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<SerieDTO>> getByGenre(@PathVariable String categorie){
        return ResponseEntity.ok().body(service.findByGenre(categorie));
    }
}

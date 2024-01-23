package br.com.persisteJpa.controller;

import br.com.persisteJpa.dto.SerieDTO;
import br.com.persisteJpa.entities.Serie;
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
}

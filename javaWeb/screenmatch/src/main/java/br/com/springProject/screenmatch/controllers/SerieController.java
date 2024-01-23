package br.com.springProject.screenmatch.controllers;

import br.com.springProject.screenmatch.entity.Serie;
import br.com.springProject.screenmatch.service.SerieService;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/serie")
public class SerieController {

    @Autowired
    private SerieService serieServ;

    @GetMapping
    public List<Serie> getSeries(){
        return serieServ.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Serie> getById(@PathVariable long id){
        var serie = serieServ.findById(id);
        return ResponseEntity.ok().body(serie);
    }

    @PostMapping()
    public ResponseEntity<Void> add(@RequestBody Serie serie){
        var newSerie = serieServ.save(serie);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newSerie.getId()).toUri();
        return ResponseEntity.created(location).build();
    }



}

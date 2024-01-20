package br.com.springProject.screenmatch.controllers;


import br.com.springProject.screenmatch.entity.Serie;
import br.com.springProject.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}

package br.com.springProject.screenmatch.service;

import br.com.springProject.screenmatch.entity.Serie;
import br.com.springProject.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Service
public class SerieService{

    @Autowired
    private SerieRepository rep;

    public Serie save(Serie s){
        return rep.save(s);
    }

    public Serie findById(Long id){
        var opt = rep.findById(id);
        return opt.orElse(new Serie());
    }

    public List<Serie> findAll() {
        return rep.findAll();
    }



}

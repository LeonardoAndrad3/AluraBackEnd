package br.com.persisteJpa.controller.exceptions;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class NotFoundPag {

    @GetMapping
    public String error(){
        return "Err my brother";
    }

}

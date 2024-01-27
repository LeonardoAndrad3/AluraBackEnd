package med.voll.api.controller;

import jakarta.transaction.Transactional;
import med.voll.api.medico.MedicoDTO;
import med.voll.api.medico.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    @Transactional
    public void save(@RequestBody MedicoDTO medico){
        service.save(medico);
    }
}

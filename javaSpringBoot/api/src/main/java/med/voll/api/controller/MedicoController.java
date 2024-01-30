package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private MedicoService service;

    @PostMapping
    @Transactional
    public void save(@RequestBody @Valid MedicoDTO medico){
        service.save(medico);
    }

    @GetMapping
    //Pageable is used when need page limit to get data from database
    public Page<MedicosListDTO> listar(@PageableDefault(size= 10, sort = {"nome"}) Pageable pag){
        return repository.findAll(pag).map(MedicosListDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid MedicoCadDTO data){
        var medico = repository.getReferenceById(data.id());
        medico.atualizarInformacoes(data);
    }
}

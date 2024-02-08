package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid CadastroPacienteDTO data, UriComponentsBuilder builder){
        var saved = repository.save(new Paciente(data));
        var uri = builder.path("/pacientes/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(new CadastroPacienteDTO(saved));
    }

    @GetMapping
    public ResponseEntity<Page<ListPacienteDTO>> findAll(@PageableDefault(size = 10, sort = {"nome"}, direction = Sort.Direction.DESC) Pageable pg){
        var pacientes = repository.findAllByAtivoTrue(pg).map(ListPacienteDTO::new);
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid AtualizacaoPacienteDTO dto){
        var paciente = repository.getReferenceById(dto.id());
        paciente.atualizarInformacoes(dto);
        return ResponseEntity.ok(new CadastroPacienteDTO(paciente));
    }
}

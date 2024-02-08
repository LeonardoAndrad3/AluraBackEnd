package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.ConsultaAgendamentoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consulta")
public class ConsultaContoller {


    @PostMapping
    @Transactional
    public void agendar(@RequestBody @Valid ConsultaAgendamentoDTO data){

        System.out.println(data);

//        return ResponseEntity.ok(new DetalhamentoDTO(null, null, null, null)).build();
    }



}

package med.voll.api.domain.consulta.validacoes;


import med.voll.api.domain.consulta.ConsultaAgendamentoDTO;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComConsultaNoMesmoHorario implements ValidadorAgendamentoConsulta {

    @Autowired
    private ConsultaRepository cRep;

    public void validar(ConsultaAgendamentoDTO dados){
        var medicoComConsultaNoMesmoHorario = cRep.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if(medicoComConsultaNoMesmoHorario)
            throw new ValidacaoException("Médico ja possui consulta nesse horario");
    }



}

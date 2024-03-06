package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaAgendamentoDTO;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ValidadorPacienteConsultaNoMesmoHorario implements ValidadorAgendamentoConsulta{

    @Autowired
    private ConsultaRepository cRep;

    public void validar(ConsultaAgendamentoDTO dados){

        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);

        var pacienteComMaisDeUmaConsultaNoDia = cRep.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        if(pacienteComMaisDeUmaConsultaNoDia)
            throw new ValidacaoException("Paciente já possui uma consulta nesse dia");
    }
}

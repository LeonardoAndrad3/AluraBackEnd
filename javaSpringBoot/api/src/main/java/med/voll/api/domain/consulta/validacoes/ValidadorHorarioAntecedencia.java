package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaAgendamentoDTO;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsulta{

    public void validar(ConsultaAgendamentoDTO dados){
        var dataConsulta =  dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30)
            throw new ValidacaoException("Consulta deve ser agendada com 30 minutos de antecedência");

    }
}

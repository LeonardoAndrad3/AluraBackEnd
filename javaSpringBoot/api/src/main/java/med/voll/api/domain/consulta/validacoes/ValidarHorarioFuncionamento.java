package med.voll.api.domain.consulta.validacoes;


import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaAgendamentoDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidarHorarioFuncionamento implements ValidadorAgendamentoConsulta {

    public void validar(ConsultaAgendamentoDTO dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDaEncerramentoDaClinica = dataConsulta.getHour() > 18;
        if(domingo || antesDaAberturaDaClinica || depoisDaEncerramentoDaClinica)
            throw new ValidationException("Consulta fora do horário de agendamento");
    }

}

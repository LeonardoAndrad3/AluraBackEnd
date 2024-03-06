package med.voll.api.domain.consulta;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record ConsultaAgendamentoDTO(

        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade

) {

    public ConsultaAgendamentoDTO(Consultas consulta) {
        this(consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData(), consulta.getMedico().getEspecialidade());

    }
}

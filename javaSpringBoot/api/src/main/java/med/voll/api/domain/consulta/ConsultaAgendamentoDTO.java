package med.voll.api.domain.consulta;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaAgendamentoDTO(

        Long idMedico,

        @NotNull
        Long IdPaciente,

        @NotNull
        @Future
        LocalDateTime data



) {


}

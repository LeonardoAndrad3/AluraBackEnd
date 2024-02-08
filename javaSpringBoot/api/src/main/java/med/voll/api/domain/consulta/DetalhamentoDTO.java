package med.voll.api.domain.consulta;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;

public record DetalhamentoDTO(

        @NotNull
        Long id,

        @Embedded
        ConsultaAgendamentoDTO agendamentoDTO
) {
    public DetalhamentoDTO(Long id, ConsultaAgendamentoDTO agendamentoDTO) {
        this.id = id;
        this.agendamentoDTO = agendamentoDTO;
    }
}

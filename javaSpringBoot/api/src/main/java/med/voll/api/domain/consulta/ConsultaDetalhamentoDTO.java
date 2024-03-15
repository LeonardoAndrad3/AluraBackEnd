package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record ConsultaDetalhamentoDTO(Long id, Long idMedic, Long idPaciente, LocalDateTime date) {
    public ConsultaDetalhamentoDTO(Consultas data) {
        this(data.getId(),data.getMedico().getId(), data.getPaciente().getId(),data.getData());
    }
}

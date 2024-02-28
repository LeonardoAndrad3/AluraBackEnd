package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record ConsultaDetalhamentoDTO(Long id, Long idMedic, Long idPaciente, LocalDateTime date) {
}

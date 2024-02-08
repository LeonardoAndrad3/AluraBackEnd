package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotBlank;

public record ListPacienteDTO(
        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank
        String cpf
) {

        public ListPacienteDTO(Paciente data) {
                this(data.getNome(), data.getEmail(), data.getCpf());
        }
}

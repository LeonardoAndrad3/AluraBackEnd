package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.EnderecoDTO;

public record CadastroPacienteDTO(

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String cpf,
        @NotNull
        @Valid
        EnderecoDTO endereco

) {
        public CadastroPacienteDTO(Paciente data) {
                this(data.getNome(), data.getEmail(), data.getTelefone(), data.getCpf(), new EnderecoDTO(data.getEndereco()));
        }
}

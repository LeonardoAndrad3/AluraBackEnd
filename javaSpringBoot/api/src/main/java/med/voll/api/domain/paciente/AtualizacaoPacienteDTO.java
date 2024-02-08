package med.voll.api.domain.paciente;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.EnderecoDTO;


@JsonIgnoreProperties(ignoreUnknown = true)
public record AtualizacaoPacienteDTO(

        @NotNull
        Long id,
        String nome,
        String telefone,
        @Valid
        EnderecoDTO endereco

) {
}

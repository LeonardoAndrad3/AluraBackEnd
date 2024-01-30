package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.EnderecoDTO;

public record MedicoCadDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco
) {
}

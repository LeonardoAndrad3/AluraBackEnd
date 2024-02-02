package med.voll.api.domain.medico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicosListDTO(
        Long id,
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade
) {

        public MedicosListDTO(Medico m){
                this(m.getId(),m.getNome(),m.getEmail(),m.getCrm(),m.getEspecialidade());
        }

}

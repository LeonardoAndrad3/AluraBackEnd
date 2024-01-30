package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.endereco.Endereco;
import med.voll.api.utils.Validation;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public void atualizarInformacoes(MedicoCadDTO data) {
        if(Validation.isNull(data.nome())) this.nome = data.nome();
        if(Validation.isNull(data.telefone())) this.telefone = data.telefone();
        if(data.endereco() != null) this.endereco.atualizarInformacoes(data.endereco());
    }
}

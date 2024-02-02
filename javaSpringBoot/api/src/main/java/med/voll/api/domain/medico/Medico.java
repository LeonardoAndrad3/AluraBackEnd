package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.utils.Validation;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "medicos")
@ToString
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

    private Boolean ativo;

    public Medico(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.crm = crm;
        this.especialidade = especialidade;
        this.endereco = endereco;
        this.ativo = true;
    }

    public void atualizarInformacoes(MedicoCadDTO data) {
        if(!Validation.isNull(data.nome())) this.nome = data.nome();
        if(!Validation.isNull(data.telefone())) this.telefone = data.telefone();
        if(data.endereco() != null) this.endereco.atualizarInformacoes(data.endereco());

    }

    public void inativar() {
        this.ativo = false;
    }
}

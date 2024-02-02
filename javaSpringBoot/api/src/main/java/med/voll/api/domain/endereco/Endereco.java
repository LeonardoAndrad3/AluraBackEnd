package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.utils.Validation;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public void atualizarInformacoes(EnderecoDTO data) {
        if(Validation.isNull(data.logradouro())) this.logradouro = data.logradouro();
        if(Validation.isNull(data.bairro())) this.bairro = data.bairro();
        if(Validation.isNull(data.cep())) this.cep = data.cep();
        if(Validation.isNull(data.numero())) this.numero = data.numero();
        if(Validation.isNull(data.complemento())) this.complemento = data.complemento();
        if(Validation.isNull(data.cidade())) this.cidade = data.cidade();
        if(Validation.isNull(data.uf())) this.uf = data.uf();
    }
}

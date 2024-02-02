package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.Endereco;

public record MedicoDetalhamentoDTO(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
) {

    public MedicoDetalhamentoDTO(Medico data) {
        this(data.getId(), data.getNome(), data.getEmail(), data.getCrm(), data.getTelefone(), data.getEspecialidade(), data.getEndereco());
    }



}


package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaAgendamentoDTO;

public interface ValidadorAgendamentoConsulta {

    void validar(ConsultaAgendamentoDTO dados);
}

package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaAgendamentoDTO;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarPacientesAtivos implements ValidadorAgendamentoConsulta{

    @Autowired
    private PacienteRepository pRep;

    public void validar(ConsultaAgendamentoDTO dadas){

        if(dadas.idPaciente() == null)
            return;

        var paciente = pRep.findAtivioById(dadas.idPaciente());

        if(!paciente)
            throw new ValidacaoException("Paciente não ativo no sistema");
    }
}

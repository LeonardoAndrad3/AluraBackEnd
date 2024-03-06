package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaAgendamentoDTO;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicosAtivos implements ValidadorAgendamentoConsulta{

    @Autowired
    private MedicoRepository mRep;

    public void validar(ConsultaAgendamentoDTO dadas){

        if(dadas.idMedico() == null)
            return;

        var medico = mRep.findByIdAndAtivoTrue(dadas.idMedico());

        if(!medico)
            throw new ValidacaoException("Medico não ativo no sistema");
    }
}


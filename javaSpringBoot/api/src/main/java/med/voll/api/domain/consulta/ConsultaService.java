package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {


    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsulta> validadores;

    public ConsultaDetalhamentoDTO agendar(ConsultaAgendamentoDTO data){

        if(!pacienteRepository.existsById(data.idPaciente())) throw new ValidacaoException("Id get not found");
        if(data.idMedico() !=  null && !medicoRepository.existsById(data.idMedico())) throw new ValidacaoException("Id get not found");

        validadores.forEach(v -> v.validar(data));

        var paciente = pacienteRepository.getReferenceById(data.idPaciente());
        var medico = chooseMedico(data);
        var consulta = new Consultas(null, medico, paciente, data.data());
        consulta = consultaRepository.save(consulta);

        return new ConsultaDetalhamentoDTO(consulta);
    }

    private Medico chooseMedico(ConsultaAgendamentoDTO data) {

        if(data.idMedico() != null) return medicoRepository.getReferenceById(data.idMedico());

        if (data.especialidade() == null) throw new ValidacaoException("We need your inform the work of doctor when medico not specific.");

        var medico = medicoRepository.chooseRandomFreeDoctorInDate(data.especialidade(), data.data());

        if(medico != null)
            return medico;

        throw new RuntimeException("Not found medicos");
    }



}

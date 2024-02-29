package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {


    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    public void agendar(ConsultaAgendamentoDTO data){

        if(!pacienteRepository.existsById(data.idPaciente())) throw new ValidacaoException("Id get not found");
        if(data.idMedico() !=  null && !medicoRepository.existsById(data.idMedico())) throw new ValidacaoException("Id get not found");

        var paciente = pacienteRepository.getReferenceById(data.idPaciente());
        var medico = chooseMedico(data);
        var consulta = new Consultas(null, medico, paciente, data.data());
        consultaRepository.save(consulta);
    }

    private Medico chooseMedico(ConsultaAgendamentoDTO data) {

        if(data.idMedico() != null) return medicoRepository.getReferenceById(data.idMedico());

        if (data.especialidade() == null) throw new ValidacaoException("We need your inform the work of doctor when medico not specific.");

        return medicoRepository.chooseRandomFreeDoctorInDate(data.especialidade(), data.data());

    }



}

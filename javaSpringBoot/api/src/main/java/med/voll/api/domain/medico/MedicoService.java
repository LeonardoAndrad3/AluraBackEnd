package med.voll.api.domain.medico;

import lombok.NoArgsConstructor;
import med.voll.api.utils.ConverterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class MedicoService {

    @Autowired
    private MedicoRepository rep;

    public Medico save(MedicoDTO dto){
        var medico = ConverterClass.convertToMedico(dto);
        return rep.save(medico);
    }


}

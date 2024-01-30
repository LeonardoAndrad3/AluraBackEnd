package med.voll.api.medico;

import lombok.NoArgsConstructor;
import med.voll.api.utils.ConverterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class MedicoService {

    @Autowired
    private MedicoRepository rep;

    public void save(MedicoDTO dto){
        var medico = ConverterClass.convertToMedico(dto);
        rep.save(medico);
    }


}

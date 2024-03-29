package med.voll.api.utils
        ;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.endereco.EnderecoDTO;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoDTO;

public class ConverterClass {

    public static Medico convertToMedico(MedicoDTO dto){
        return new Medico(null, dto.nome(), dto.email(), dto.telefone(), dto.crm(), dto.especialidade(), convertToEndereco(dto.endereco()));
    }

    public static MedicoDTO convertToMedico(Medico m){
        return new MedicoDTO(m.getNome(), m.getEmail(), m.getTelefone(), m.getCrm(), m.getEspecialidade(),convertToEnderecoDTO(m.getEndereco()));
    }



    public static Endereco convertToEndereco(EnderecoDTO dto){
        return new Endereco(dto.logradouro(), dto.bairro(), dto.cep(), dto.numero(), dto.complemento(), dto.cidade(), dto.uf());
    }

    public static EnderecoDTO convertToEnderecoDTO(Endereco e){
        return new EnderecoDTO(e.getLogradouro(), e.getBairro(), e.getCep(), e.getNumero(), e.getComplemento(), e.getCidade(), e.getUf());
    }



}

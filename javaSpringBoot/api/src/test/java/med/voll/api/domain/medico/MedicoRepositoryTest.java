package med.voll.api.domain.medico;

import med.voll.api.domain.consulta.Consultas;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.endereco.EnderecoDTO;
import med.voll.api.domain.paciente.CadastroPacienteDTO;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
//Teste para interface repository
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Devolver null quando medico cadastrado não esta disponivel na data")
    void chooseRandomFreeDoctorInDateCenario1() {

        //given or arrange
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@gmail.com", "00000000000");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        //when or act
        var medicoLivre = medicoRepository.chooseRandomFreeDoctorInDate(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        //then or assert
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Devolver medico cadastrado disponivel na data")
    void chooseRandomFreeDoctorInDateCenario2() {
        //given or arrange
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

        //when or act
        var medicoLivre = medicoRepository.chooseRandomFreeDoctorInDate(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        //then or assert
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consultas(null, medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private MedicoDTO dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new MedicoDTO(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEnderecoDTO()
        );
    }

    private CadastroPacienteDTO dadosPaciente(String nome, String email, String cpf) {
        return new CadastroPacienteDTO(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEnderecoDTO()
        );
    }

    private EnderecoDTO dadosEnderecoDTO() {
        return new EnderecoDTO(
                "rua xpto",
                "Jd nazareth",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }


}
package med.voll.api.controller;

import med.voll.api.domain.consulta.ConsultaAgendamentoDTO;
import med.voll.api.domain.consulta.ConsultaDetalhamentoDTO;
import med.voll.api.domain.consulta.ConsultaService;
import med.voll.api.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    //Teste unitario com Mock
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ConsultaAgendamentoDTO> consultaAgendamentoDTOJacksonTester;

    @Autowired
    private JacksonTester<ConsultaDetalhamentoDTO> consultaDetalhamentoDTOJacksonTester;

    @MockBean
    private ConsultaService service;

    @Test
    @DisplayName("Codigo http 400 quando informaçõoes invalidas")
    @WithMockUser
    void agendar_cenario1() throws Exception {

        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Codigo http 200 quando informaçõoes sao validas")
    @WithMockUser
    void agendar_cenario2() throws Exception {

        var data = LocalDateTime.now().plusHours(1);
        var especildiade = Especialidade.CARDIOLOGIA;

        var detalhamento = new ConsultaDetalhamentoDTO(null, 2l, 5l, data);
        when(service.agendar(any())).thenReturn(detalhamento);

        var response = mvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(consultaAgendamentoDTOJacksonTester.write(
                                new ConsultaAgendamentoDTO(2l, 5l, data, especildiade)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = consultaDetalhamentoDTOJacksonTester.write(
                new ConsultaDetalhamentoDTO(null, 2l, 5l, data)
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


}
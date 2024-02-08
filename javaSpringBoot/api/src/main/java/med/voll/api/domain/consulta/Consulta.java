package med.voll.api.domain.consulta;


import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;

//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(of = "id")
//public class Consulta {
//
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "medico_id")
//    private Medico medico;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "paciente_id")
//    private Paciente paciente;
//
//    private LocalDateTime data;
//
//
//}

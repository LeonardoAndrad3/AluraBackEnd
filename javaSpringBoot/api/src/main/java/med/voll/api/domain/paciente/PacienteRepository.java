package med.voll.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {


    Page<Paciente> findAllByAtivoTrue(Pageable pg);


    @Query("""
            select p.ativo from Paciente p
            where
            p.id = :id
            """)
    boolean findAtivioById(Long id);


}

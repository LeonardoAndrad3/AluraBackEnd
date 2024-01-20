package br.com.springProject.screenmatch.repository;

import br.com.springProject.screenmatch.entity.Serie;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SerieRepository extends JpaRepository<Serie, Long> {
}

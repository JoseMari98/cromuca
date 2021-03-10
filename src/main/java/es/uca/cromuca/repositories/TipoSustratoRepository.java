package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.enums.TipoSustrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoSustratoRepository extends JpaRepository<TipoSustrato, Long> {
    List<TipoSustrato> findByTipoSustratoStartsWithIgnoreCase(String nombre);
}

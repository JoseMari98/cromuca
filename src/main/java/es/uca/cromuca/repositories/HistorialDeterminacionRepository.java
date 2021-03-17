package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.entities.HistorialDeterminacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialDeterminacionRepository extends JpaRepository<HistorialDeterminacion, Long> {
    HistorialDeterminacion findByEspecie(Especie especie);
}

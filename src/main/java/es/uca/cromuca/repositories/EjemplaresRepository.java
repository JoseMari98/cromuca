package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.Ejemplares;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjemplaresRepository extends JpaRepository<Ejemplares, Long> {
}

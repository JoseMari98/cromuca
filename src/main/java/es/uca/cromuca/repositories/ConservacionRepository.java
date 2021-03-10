package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.Conservacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConservacionRepository extends JpaRepository<Conservacion, Long> {
}

package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.enums.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, Long> {
}

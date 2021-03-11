package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.enums.Habitat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitatRepository extends JpaRepository<Habitat, Long> {
}

package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}

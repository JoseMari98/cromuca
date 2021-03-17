package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.Prestamo;
import es.uca.cromuca.entities.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByUbicacion(Ubicacion ubicacion);
}

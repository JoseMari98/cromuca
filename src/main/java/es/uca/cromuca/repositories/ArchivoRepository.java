package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoRepository extends JpaRepository<Archivo, Long> {
}

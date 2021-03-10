package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.enums.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaisRepository extends JpaRepository<Pais, Long> {
    List<Pais> findByPaisStartsWithIgnoreCase(String nombre);

}

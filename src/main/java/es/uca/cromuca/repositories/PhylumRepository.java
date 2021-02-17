package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.Phylum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhylumRepository extends JpaRepository<Phylum, Long> {
    List<Phylum> findByPhylumStartsWithIgnoreCase(String nombre);
}

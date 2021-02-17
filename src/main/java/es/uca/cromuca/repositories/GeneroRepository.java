package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.Familia;
import es.uca.cromuca.entities.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GeneroRepository extends JpaRepository<Genero, Long> {
    List<Genero> findByGeneroStartsWithIgnoreCase(String nombre);

    List<Genero> findByFamilia(Familia familia);
}

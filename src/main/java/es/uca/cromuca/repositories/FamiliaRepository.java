package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.CategoriaTaxonomicaPpal;
import es.uca.cromuca.entities.Familia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamiliaRepository extends JpaRepository<Familia, Long> {
    List<Familia> findByFamiliaStartsWithIgnoreCase(String nombre);

    List<Familia> findByCategoriaTaxonomicaPpal(CategoriaTaxonomicaPpal categoriaTaxonomicaPpal);
}

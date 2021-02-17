package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.CategoriaTaxonomicaPpal;
import es.uca.cromuca.entities.Phylum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaTaxonomicaRepository extends JpaRepository<CategoriaTaxonomicaPpal, Long> {
    List<CategoriaTaxonomicaPpal> findByCategoriaTaxonomicaPpalStartsWithIgnoreCase(String nombre);

    List<CategoriaTaxonomicaPpal> findByPhylum(Phylum phylum);
}

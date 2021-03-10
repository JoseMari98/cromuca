package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.enums.MetodoCaptura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetodoCapturaRepository extends JpaRepository<MetodoCaptura, Long> {
    List<MetodoCaptura> findByMetodoCapturaStartsWithIgnoreCase(String nombre);
}

package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.entities.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EspecieRepository extends JpaRepository<Especie, Long> {
    List<Especie> findByGenero(Genero genero);

    Especie findTopByOrderByIdDesc();

    Especie findByNumeroCatalogoAndNumeroFrasco(String numeroCatalogo, String numeroFrasco);

    List<Especie> findAllByOrderByNumeroCatalogoDesc();

    List<Especie> findByNumeroCatalogoOrderByNumeroFrascoDesc(String numeroCatalogo);
}

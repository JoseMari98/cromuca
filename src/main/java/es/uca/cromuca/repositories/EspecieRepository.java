package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EspecieRepository extends JpaRepository<Especie, Long> {
    List<Especie> findByGenero(Genero genero);

    Especie findTopByOrderByIdDesc();

    Especie findByNumeroCatalogoAndNumeroFrasco(String numeroCatalogo, String numeroFrasco);

    List<Especie> findAllByOrderByNumeroCatalogoDesc();

    List<Especie> findByNumeroCatalogoOrderByNumeroFrascoDesc(String numeroCatalogo);

    /*List<Especie> findByNumeroCatalogoAndPhylum(String numeroCatalogo, Phylum phylum);

    List<Especie> findByNumeroCatalogoAndCategoriaTaxonomicaPpal(String numeroCatalogo, CategoriaTaxonomicaPpal categoriaTaxonomicaPpal);

    List<Especie> findByNumeroCatalogoAndFamilia(String numeroCatalogo, Familia familia);

    List<Especie> findByNumeroCatalogoAndGenero(String numeroCatalogo, Genero genero);*/

    List<Especie> findByEspecie(Especie especie);

    List<Especie> findByPhylum(Phylum phylum);

    List<Especie> findByCategoriaTaxonomicaPpal(CategoriaTaxonomicaPpal categoriaTaxonomicaPpal);

    List<Especie> findByFamilia(Familia familia);
}

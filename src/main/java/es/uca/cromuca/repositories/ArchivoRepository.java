package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.Archivo;
import es.uca.cromuca.entities.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoRepository extends JpaRepository<Archivo, Long> {
    Archivo findByEspecie(Especie especie);
}

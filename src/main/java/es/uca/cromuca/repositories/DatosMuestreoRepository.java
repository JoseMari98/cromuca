package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.DatosMuestreo;
import es.uca.cromuca.entities.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatosMuestreoRepository extends JpaRepository<DatosMuestreo, Long> {
    DatosMuestreo findByEspecie(Especie especie);
}

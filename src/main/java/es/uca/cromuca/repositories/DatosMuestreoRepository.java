package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.DatosMuestreo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatosMuestreoRepository extends JpaRepository<DatosMuestreo, Long> {
}

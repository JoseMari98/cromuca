package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.entities.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
    Ubicacion findByEspecie(Especie especie);
}

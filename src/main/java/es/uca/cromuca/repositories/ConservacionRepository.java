package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.Conservacion;
import es.uca.cromuca.entities.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConservacionRepository extends JpaRepository<Conservacion, Long> {
    Conservacion findByEspecie(Especie especie);

}

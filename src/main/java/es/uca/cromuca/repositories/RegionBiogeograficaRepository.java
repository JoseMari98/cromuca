package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.enums.RegionBiogeografica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionBiogeograficaRepository extends JpaRepository<RegionBiogeografica, Long> {
}

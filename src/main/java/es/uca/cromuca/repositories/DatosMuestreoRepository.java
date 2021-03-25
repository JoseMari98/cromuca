package es.uca.cromuca.repositories;

import es.uca.cromuca.entities.DatosMuestreo;
import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.entities.enums.TipoSustrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatosMuestreoRepository extends JpaRepository<DatosMuestreo, Long> {
    DatosMuestreo findByEspecie(Especie especie);

    List<DatosMuestreo> findByRegionBiogeografica(String regionBiogeografica);

    List<DatosMuestreo> findByRegionBiogeograficaAndRegionMarina(String regionBiogeografica, String regionMarina);

    List<DatosMuestreo> findByRegionBiogeograficaAndRegionMarinaAndHabitat(String regionBiogeografica, String regionMarina, String habitat);

    List<DatosMuestreo> findByRegionBiogeograficaAndRegionMarinaAndHabitatAndTipoSustrato(String regionBiogeografica, String regionMarina, String habitat, TipoSustrato tipoSustrato);

    List<DatosMuestreo> findByRegionBiogeograficaAndHabitat(String regionBiogeografica, String habitat);

    List<DatosMuestreo> findByRegionBiogeograficaAndHabitatAndTipoSustrato(String regionBiogeografica, String habitat, TipoSustrato tipoSustrato);

    List<DatosMuestreo> findByRegionBiogeograficaAndTipoSustrato(String regionBiogeografica, TipoSustrato tipoSustrato);

    List<DatosMuestreo> findByRegionBiogeograficaAndRegionMarinaAndTipoSustrato(String regionBiogeografica, String regionMarina, TipoSustrato tipoSustrato);

    List<DatosMuestreo> findByRegionMarina(String regionMarina);

    List<DatosMuestreo> findByRegionMarinaAndHabitat(String regionMarina, String habitat);

    List<DatosMuestreo> findByRegionMarinaAndTipoSustrato(String regionMarina, TipoSustrato tipoSustrato);

    List<DatosMuestreo> findByRegionMarinaAndHabitatAndTipoSustrato(String regionMarina, String habitat, TipoSustrato tipoSustrato);

    List<DatosMuestreo> findByHabitat(String habitat);

    List<DatosMuestreo> findByHabitatAndTipoSustrato(String habitat, TipoSustrato tipoSustrato);

    List<DatosMuestreo> findByTipoSustrato(TipoSustrato tipoSustrato);
}

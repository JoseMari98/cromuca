package es.uca.cromuca.services.enums;

import es.uca.cromuca.entities.enums.RegionBiogeografica;
import es.uca.cromuca.repositories.RegionBiogeograficaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionBiogeograficaService {
    private RegionBiogeograficaRepository regionBiogeograficaRepository;

    @Autowired
    private RegionBiogeograficaService(RegionBiogeograficaRepository regionBiogeograficaRepository) {
        this.regionBiogeograficaRepository = regionBiogeograficaRepository;
    }

    public RegionBiogeografica guardar(RegionBiogeografica entrada) {
        return regionBiogeograficaRepository.save(entrada);
    }

    public Optional<RegionBiogeografica> buscarId(Long id) {
        return regionBiogeograficaRepository.findById(id);
    }

    public List<RegionBiogeografica> findAll() {
        return regionBiogeograficaRepository.findAll();
    }

    public void borrar(RegionBiogeografica entrada) {
        regionBiogeograficaRepository.delete(entrada);
    }
}

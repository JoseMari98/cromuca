package es.uca.cromuca.services.enums;

import es.uca.cromuca.entities.enums.RegionMarina;
import es.uca.cromuca.repositories.RegionMarinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionMarinaService {
    private RegionMarinaRepository regionMarinaRepository;

    @Autowired
    private RegionMarinaService(RegionMarinaRepository regionMarinaRepository) {
        this.regionMarinaRepository = regionMarinaRepository;
    }

    public RegionMarina guardar(RegionMarina entrada) {
        return regionMarinaRepository.save(entrada);
    }

    public Optional<RegionMarina> buscarId(Long id) {
        return regionMarinaRepository.findById(id);
    }

    public List<RegionMarina> findAll() {
        return regionMarinaRepository.findAll();
    }

    public void borrar(RegionMarina entrada) {
        regionMarinaRepository.delete(entrada);
    }
}

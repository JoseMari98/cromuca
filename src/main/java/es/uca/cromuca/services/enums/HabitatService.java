package es.uca.cromuca.services.enums;

import es.uca.cromuca.entities.enums.Habitat;
import es.uca.cromuca.repositories.HabitatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitatService {
    private HabitatRepository habitatRepository;

    @Autowired
    private HabitatService(HabitatRepository habitatRepository) {
        this.habitatRepository = habitatRepository;
    }

    public Habitat guardar(Habitat entrada) {
        return habitatRepository.save(entrada);
    }

    public Optional<Habitat> buscarId(Long id) {
        return habitatRepository.findById(id);
    }

    public List<Habitat> findAll() {
        return habitatRepository.findAll();
    }

    public void borrar(Habitat entrada) {
        habitatRepository.delete(entrada);
    }
}

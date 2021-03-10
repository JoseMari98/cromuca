package es.uca.cromuca.services.enums;

import es.uca.cromuca.entities.enums.Pais;
import es.uca.cromuca.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisService {
    private PaisRepository paisRepository;

    @Autowired
    private PaisService(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    public Pais guardar(Pais entrada) {
        return paisRepository.save(entrada);
    }

    public Optional<Pais> buscarId(Long id) {
        return paisRepository.findById(id);
    }

    public List<Pais> findAll() {
        return paisRepository.findAll();
    }

    public void borrar(Pais entrada) {
        paisRepository.delete(entrada);
    }
}

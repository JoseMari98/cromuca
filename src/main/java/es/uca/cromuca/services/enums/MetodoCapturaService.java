package es.uca.cromuca.services.enums;

import es.uca.cromuca.entities.enums.MetodoCaptura;
import es.uca.cromuca.repositories.MetodoCapturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetodoCapturaService {
    private MetodoCapturaRepository metodoCapturaRepository;

    @Autowired
    private MetodoCapturaService(MetodoCapturaRepository metodoCapturaRepository) {
        this.metodoCapturaRepository = metodoCapturaRepository;
    }

    public MetodoCaptura guardar(MetodoCaptura entrada) {
        return metodoCapturaRepository.save(entrada);
    }

    public Optional<MetodoCaptura> buscarId(Long id) {
        return metodoCapturaRepository.findById(id);
    }

    public List<MetodoCaptura> findAll() {
        return metodoCapturaRepository.findAll();
    }

    public void borrar(MetodoCaptura entrada) {
        metodoCapturaRepository.delete(entrada);
    }
}

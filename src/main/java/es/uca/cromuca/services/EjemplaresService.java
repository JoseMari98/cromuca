package es.uca.cromuca.services;

import es.uca.cromuca.entities.Ejemplares;
import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.repositories.EjemplaresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EjemplaresService {
    private EjemplaresRepository ejemplaresRepository;

    @Autowired
    private EjemplaresService(EjemplaresRepository ejemplaresRepository) {
        this.ejemplaresRepository = ejemplaresRepository;
    }

    public Ejemplares guardar(Ejemplares entrada) {
        return ejemplaresRepository.save(entrada);
    }

    public Optional<Ejemplares> buscarId(Long id) {
        return ejemplaresRepository.findById(id);
    }

    public List<Ejemplares> findAll() {
        return ejemplaresRepository.findAll();
    }

    public void borrar(Ejemplares entrada) {
        ejemplaresRepository.delete(entrada);
    }

    public Ejemplares findByEspecie(Especie especie) {
        return ejemplaresRepository.findByEspecie(especie);
    }
}

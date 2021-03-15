package es.uca.cromuca.services;

import es.uca.cromuca.entities.Conservacion;
import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.repositories.ConservacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConservacionService {
    private ConservacionRepository conservacionRepository;

    @Autowired
    private ConservacionService(ConservacionRepository conservacionRepository) {
        this.conservacionRepository = conservacionRepository;
    }

    public Conservacion guardar(Conservacion entrada) {
        return conservacionRepository.save(entrada);
    }

    public Optional<Conservacion> buscarId(Long id) {
        return conservacionRepository.findById(id);
    }

    public List<Conservacion> findAll() {
        return conservacionRepository.findAll();
    }

    public void borrar(Conservacion entrada) {
        conservacionRepository.delete(entrada);
    }

    public Conservacion findByEspecie(Especie especie) {
        return conservacionRepository.findByEspecie(especie);
    }
}

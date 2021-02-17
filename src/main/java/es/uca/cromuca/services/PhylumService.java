package es.uca.cromuca.services;

import es.uca.cromuca.entities.Phylum;
import es.uca.cromuca.repositories.PhylumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhylumService {
    private PhylumRepository phylumRepository;

    @Autowired
    private PhylumService(PhylumRepository phylumRepository) {
        this.phylumRepository = phylumRepository;
    }

    public Phylum guardar(Phylum entrada) {
        return phylumRepository.save(entrada);
    }

    public Optional<Phylum> buscarIdModelo(Long id) {
        return phylumRepository.findById(id);
    }

    public List<Phylum> findAll() {
        return phylumRepository.findAll();
    }

    public void borrar(Phylum entrada) {
        phylumRepository.delete(entrada);
    }

    public List<Phylum> findByPhylum(String phylum) {
        return this.phylumRepository.findByPhylumStartsWithIgnoreCase(phylum);
    }
}

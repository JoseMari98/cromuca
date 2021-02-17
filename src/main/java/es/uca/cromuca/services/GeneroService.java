package es.uca.cromuca.services;

import es.uca.cromuca.entities.Familia;
import es.uca.cromuca.entities.Genero;
import es.uca.cromuca.repositories.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {
    private GeneroRepository generoRepository;

    @Autowired
    private GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public Genero guardar(Genero entrada) {
        return generoRepository.save(entrada);
    }

    public Optional<Genero> buscarIdModelo(Long id) {
        return generoRepository.findById(id);
    }

    public List<Genero> findAll() {
        return generoRepository.findAll();
    }

    public void borrar(Genero entrada) {
        generoRepository.delete(entrada);
    }

    public List<Genero> findByGenero(String genero) {
        return this.generoRepository.findByGeneroStartsWithIgnoreCase(genero);
    }

    public List<Genero> findByFamilia(Familia familia) {
        return this.generoRepository.findByFamilia(familia);
    }
}

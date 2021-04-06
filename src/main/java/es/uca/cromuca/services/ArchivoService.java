package es.uca.cromuca.services;

import es.uca.cromuca.entities.Archivo;
import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.repositories.ArchivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArchivoService {
    private ArchivoRepository archivoRepository;

    @Autowired
    private ArchivoService(ArchivoRepository archivoRepository) {
        this.archivoRepository = archivoRepository;
    }

    public Archivo guardar(Archivo entrada) {
        return archivoRepository.save(entrada);
    }

    public Optional<Archivo> buscarId(Long id) {
        return archivoRepository.findById(id);
    }

    public List<Archivo> findAll() {
        return archivoRepository.findAll();
    }

    public void borrar(Archivo entrada) {
        archivoRepository.delete(entrada);
    }

    public List<Archivo> findByEspecie(Especie especie) {
        return archivoRepository.findByEspecie(especie);
    }
}

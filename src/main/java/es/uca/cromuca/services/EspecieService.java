package es.uca.cromuca.services;

import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.entities.Genero;
import es.uca.cromuca.repositories.EspecieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecieService {
    private EspecieRepository especieRepository;

    @Autowired
    private EspecieService(EspecieRepository especieRepository) {
        this.especieRepository = especieRepository;
    }

    public Especie guardar(Especie entrada) {
        return especieRepository.save(entrada);
    }

    public Optional<Especie> buscarId(Long id) {
        return especieRepository.findById(id);
    }

    public List<Especie> findAll() {
        return especieRepository.findAll();
    }

    public void borrar(Especie entrada) {
        especieRepository.delete(entrada);
    }

    public List<Especie> findByGenero(Genero entrada) {
        return especieRepository.findByGenero(entrada);
    }

    public Especie findLastid() {
        return especieRepository.findTopByOrderByIdDesc();
    }

    public  Especie findByNumCatalogo(String numeroCatalogo){
        return especieRepository.findByNumeroCatalogo(numeroCatalogo);
    }
}

package es.uca.cromuca.services;

import es.uca.cromuca.entities.CategoriaTaxonomicaPpal;
import es.uca.cromuca.entities.Familia;
import es.uca.cromuca.repositories.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamiliaService {
    private FamiliaRepository familiaRepository;

    @Autowired
    private FamiliaService(FamiliaRepository familiaRepository) {
        this.familiaRepository = familiaRepository;
    }

    public Familia guardar(Familia entrada) {
        return familiaRepository.save(entrada);
    }

    public Optional<Familia> buscarId(Long id) {
        return familiaRepository.findById(id);
    }

    public List<Familia> findAll() {
        return familiaRepository.findAll();
    }

    public void borrar(Familia entrada) {
        familiaRepository.delete(entrada);
    }

    public List<Familia> findByCategoria(CategoriaTaxonomicaPpal categoriaTaxonomicaPpal) {
        return this.familiaRepository.findByCategoriaTaxonomicaPpal(categoriaTaxonomicaPpal);
    }

    public List<Familia> findByFamilia(String familia) {
        return this.familiaRepository.findByFamiliaStartsWithIgnoreCase(familia);
    }
}

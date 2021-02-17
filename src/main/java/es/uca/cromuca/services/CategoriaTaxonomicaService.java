package es.uca.cromuca.services;

import es.uca.cromuca.entities.CategoriaTaxonomicaPpal;
import es.uca.cromuca.entities.Phylum;
import es.uca.cromuca.repositories.CategoriaTaxonomicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaTaxonomicaService {
    private CategoriaTaxonomicaRepository categoriaTaxonomicaRepository;

    @Autowired
    private CategoriaTaxonomicaService(CategoriaTaxonomicaRepository categoriaTaxonomicaRepository) {
        this.categoriaTaxonomicaRepository = categoriaTaxonomicaRepository;
    }

    public CategoriaTaxonomicaPpal guardar(CategoriaTaxonomicaPpal entrada) {
        return categoriaTaxonomicaRepository.save(entrada);
    }

    public Optional<CategoriaTaxonomicaPpal> buscarIdModelo(Long id) {
        return categoriaTaxonomicaRepository.findById(id);
    }

    public List<CategoriaTaxonomicaPpal> findAll() {
        return categoriaTaxonomicaRepository.findAll();
    }

    public void borrar(CategoriaTaxonomicaPpal entrada) {
        categoriaTaxonomicaRepository.delete(entrada);
    }

    public List<CategoriaTaxonomicaPpal> findByPhylum(Phylum phylum) {
        return this.categoriaTaxonomicaRepository.findByPhylum(phylum);
    }

    public List<CategoriaTaxonomicaPpal> findByCategoriaTaxonomica(String categoria) {
        return this.categoriaTaxonomicaRepository.findByCategoriaTaxonomicaPpalStartsWithIgnoreCase(categoria);
    }
}

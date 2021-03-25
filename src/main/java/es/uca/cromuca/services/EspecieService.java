package es.uca.cromuca.services;

import es.uca.cromuca.entities.*;
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

    public Especie findByNumCatalogoAndFrasco(String numeroCatalogo, String numeroFrasco) {
        return especieRepository.findByNumeroCatalogoAndNumeroFrasco(numeroCatalogo, numeroFrasco);
    }

    public String nuevoNumero() {
        List<Especie> especieList = especieRepository.findAllByOrderByNumeroCatalogoDesc();
        Especie first = especieList.iterator().next();

        return first.getNumeroCatalogo();
    }

    public String nuevoFrasco(String numeroCatalogo) {
        List<Especie> especieList = especieRepository.findByNumeroCatalogoOrderByNumeroFrascoDesc(numeroCatalogo);
        Especie first = especieList.iterator().next();

        return first.getNumeroFrasco();
    }

    public List<Especie> findByNumeroCatalogoOrderByNumeroFrascoDesc(String numeroCatalogo) {
        return especieRepository.findByNumeroCatalogoOrderByNumeroFrascoDesc(numeroCatalogo);
    }

    public List<Especie> find(Phylum phylum, CategoriaTaxonomicaPpal categoriaTaxonomicaPpal, Familia familia, Genero genero, Especie especie) {
        //if(numeroCatalogo.isEmpty()){
        if (especie != null)
            return especieRepository.findByEspecie(especie);
        else if (genero != null)
            return especieRepository.findByGenero(genero);
        else {
            if (familia != null)
                return especieRepository.findByFamilia(familia);
            else {
                if (categoriaTaxonomicaPpal != null)
                    return especieRepository.findByCategoriaTaxonomicaPpal(categoriaTaxonomicaPpal);
                else
                    return especieRepository.findByPhylum(phylum);
            }
        }
         /*else{
            if(genero != null)
                return especieRepository.findByNumeroCatalogoAndGenero(numeroCatalogo, genero);
            else{
                if(familia != null)
                    return especieRepository.findByNumeroCatalogoAndFamilia(numeroCatalogo, familia);
                else{
                    if(categoriaTaxonomicaPpal != null)
                        return especieRepository.findByNumeroCatalogoAndCategoriaTaxonomicaPpal(numeroCatalogo, categoriaTaxonomicaPpal);
                    else
                        return especieRepository.findByNumeroCatalogoAndPhylum(numeroCatalogo, phylum);
                }
            }
        }*/
    }
}

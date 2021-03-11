package es.uca.cromuca.services;

import es.uca.cromuca.entities.DatosMuestreo;
import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.repositories.DatosMuestreoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatosMuestreoService {
    private DatosMuestreoRepository datosMuestreoRepository;

    @Autowired
    private DatosMuestreoService(DatosMuestreoRepository datosMuestreoRepository) {
        this.datosMuestreoRepository = datosMuestreoRepository;
    }

    public DatosMuestreo guardar(DatosMuestreo entrada) {
        return datosMuestreoRepository.save(entrada);
    }

    public Optional<DatosMuestreo> buscarId(Long id) {
        return datosMuestreoRepository.findById(id);
    }

    public List<DatosMuestreo> findAll() {
        return datosMuestreoRepository.findAll();
    }

    public void borrar(DatosMuestreo entrada) {
        datosMuestreoRepository.delete(entrada);
    }

    public DatosMuestreo findByEspecie(Especie especie) {
        return datosMuestreoRepository.findByEspecie(especie);
    }
}

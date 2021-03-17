package es.uca.cromuca.services;

import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.entities.Ubicacion;
import es.uca.cromuca.repositories.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UbicacionService {
    private UbicacionRepository ubicacionRepository;

    @Autowired
    private UbicacionService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public Ubicacion guardar(Ubicacion entrada) {
        return ubicacionRepository.save(entrada);
    }

    public Optional<Ubicacion> buscarId(Long id) {
        return ubicacionRepository.findById(id);
    }

    public List<Ubicacion> findAll() {
        return ubicacionRepository.findAll();
    }

    public void borrar(Ubicacion entrada) {
        ubicacionRepository.delete(entrada);
    }

    public Ubicacion findByEspecie(Especie especie) {
        return ubicacionRepository.findByEspecie(especie);
    }
}

package es.uca.cromuca.services;

import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.entities.HistorialDeterminacion;
import es.uca.cromuca.repositories.HistorialDeterminacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialDeterminacionService {
    private HistorialDeterminacionRepository historialDeterminacionRepository;

    @Autowired
    private HistorialDeterminacionService(HistorialDeterminacionRepository historialDeterminacionRepository) {
        this.historialDeterminacionRepository = historialDeterminacionRepository;
    }

    public HistorialDeterminacion guardar(HistorialDeterminacion entrada) {
        return historialDeterminacionRepository.save(entrada);
    }

    public Optional<HistorialDeterminacion> buscarId(Long id) {
        return historialDeterminacionRepository.findById(id);
    }

    public List<HistorialDeterminacion> findAll() {
        return historialDeterminacionRepository.findAll();
    }

    public void borrar(HistorialDeterminacion entrada) {
        historialDeterminacionRepository.delete(entrada);
    }

    public HistorialDeterminacion findByEspecie(Especie especie) {
        return historialDeterminacionRepository.findByEspecie(especie);
    }
}

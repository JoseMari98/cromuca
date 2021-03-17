package es.uca.cromuca.services;

import es.uca.cromuca.entities.Prestamo;
import es.uca.cromuca.entities.Ubicacion;
import es.uca.cromuca.repositories.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {
    private PrestamoRepository prestamoRepository;

    @Autowired
    private PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    public Prestamo guardar(Prestamo entrada) {
        return prestamoRepository.save(entrada);
    }

    public Optional<Prestamo> buscarId(Long id) {
        return prestamoRepository.findById(id);
    }

    public List<Prestamo> findAll() {
        return prestamoRepository.findAll();
    }

    public void borrar(Prestamo entrada) {
        prestamoRepository.delete(entrada);
    }

    public List<Prestamo> findByUbicacion(Ubicacion ubicacion) {
        return prestamoRepository.findByUbicacion(ubicacion);
    }
}

package es.uca.cromuca.services.enums;

import es.uca.cromuca.entities.enums.TipoSustrato;
import es.uca.cromuca.repositories.TipoSustratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoSustratoService {
    private TipoSustratoRepository tipoSustratoRepository;

    @Autowired
    private TipoSustratoService(TipoSustratoRepository tipoSustratoRepository) {
        this.tipoSustratoRepository = tipoSustratoRepository;
    }

    public TipoSustrato guardar(TipoSustrato entrada) {
        return tipoSustratoRepository.save(entrada);
    }

    public Optional<TipoSustrato> buscarId(Long id) {
        return tipoSustratoRepository.findById(id);
    }

    public List<TipoSustrato> findAll() {
        return tipoSustratoRepository.findAll();
    }

    public void borrar(TipoSustrato entrada) {
        tipoSustratoRepository.delete(entrada);
    }

    public List<TipoSustrato> findByTipoSustrato(String nombre) {
        return tipoSustratoRepository.findByTipoSustratoStartsWithIgnoreCase(nombre);
    }
}

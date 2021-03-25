package es.uca.cromuca.services;

import es.uca.cromuca.entities.DatosMuestreo;
import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.entities.enums.TipoSustrato;
import es.uca.cromuca.repositories.DatosMuestreoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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

    public List<Especie> concatenarListas(List<Especie> especieList, List<DatosMuestreo> datosMuestreoList) {
        List<Especie> especieList1 = new LinkedList<>();

        if (especieList.isEmpty()) {
            for (DatosMuestreo datosMuestreo : datosMuestreoList)
                especieList1.add(datosMuestreo.getEspecie());
        } else {
            for (Especie especie : especieList) {
                for (DatosMuestreo datosMuestreo : datosMuestreoList) {
                    if (especie.getId().equals(datosMuestreo.getEspecie().getId())) {
                        especieList1.add(especie);
                        break;
                    }
                }
            }
        }

        return especieList1;
    }

    public List<DatosMuestreo> find(String tipoOrganismo, String regionMarina, String regionBiogeografica, String habitat, TipoSustrato tipoSustrato) {
        List<DatosMuestreo> datosMuestreoList = new LinkedList<>();
        if (regionMarina == null) {
            if (regionBiogeografica == null) {
                if (habitat == null) {
                    if (tipoSustrato != null) //solo sustrato
                        datosMuestreoRepository.findByTipoSustrato(tipoSustrato);
                } else { //habitat y sustrato
                    if (tipoSustrato != null)
                        datosMuestreoList = datosMuestreoRepository.findByHabitatAndTipoSustrato(habitat, tipoSustrato);
                    else
                        datosMuestreoList = datosMuestreoRepository.findByHabitat(habitat);
                }
            } else { //bio no vacia
                if (habitat == null) {
                    if (tipoSustrato == null)
                        datosMuestreoList = datosMuestreoRepository.findByRegionBiogeografica(regionBiogeografica);
                    else
                        datosMuestreoList = datosMuestreoRepository.findByRegionBiogeograficaAndTipoSustrato(regionBiogeografica, tipoSustrato);
                } else {
                    if (tipoSustrato == null)
                        datosMuestreoList = datosMuestreoRepository.findByRegionBiogeograficaAndHabitat(regionBiogeografica, habitat);
                    else
                        datosMuestreoList = datosMuestreoRepository.findByRegionBiogeograficaAndHabitatAndTipoSustrato(regionBiogeografica, habitat, tipoSustrato);
                }
            }
        } else { //marina no vacia
            if (regionBiogeografica == null) {
                if (habitat == null) {
                    if (tipoSustrato == null)
                        datosMuestreoList = datosMuestreoRepository.findByRegionMarina(regionMarina);
                    else
                        datosMuestreoList = datosMuestreoRepository.findByRegionMarinaAndTipoSustrato(regionMarina, tipoSustrato);
                } else {
                    if (tipoSustrato == null)
                        datosMuestreoList = datosMuestreoRepository.findByRegionMarinaAndHabitat(regionMarina, habitat);
                    else
                        datosMuestreoList = datosMuestreoRepository.findByRegionMarinaAndHabitatAndTipoSustrato(regionMarina, habitat, tipoSustrato);
                }
            } else {
                if (habitat == null) {
                    if (tipoSustrato == null)
                        datosMuestreoList = datosMuestreoRepository.findByRegionBiogeograficaAndRegionMarina(regionBiogeografica, regionMarina);
                    else
                        datosMuestreoList = datosMuestreoRepository.findByRegionBiogeograficaAndRegionMarinaAndTipoSustrato(regionBiogeografica, regionMarina, tipoSustrato);
                } else {
                    if (tipoSustrato == null)
                        datosMuestreoList = datosMuestreoRepository.findByRegionBiogeograficaAndRegionMarinaAndHabitat(regionBiogeografica, regionMarina, habitat);
                    else
                        datosMuestreoList = datosMuestreoRepository.findByRegionBiogeograficaAndRegionMarinaAndHabitatAndTipoSustrato(regionBiogeografica, regionMarina, habitat, tipoSustrato);
                }
            }
        }
        if (tipoOrganismo != null) {
            List<DatosMuestreo> datosMuestreoListAux = new LinkedList<>();
            for (DatosMuestreo datosMuestreo : datosMuestreoList) {
                if (!datosMuestreo.getTipoOrganismo().isEmpty() && datosMuestreo.getTipoOrganismo().equals(tipoOrganismo))
                    datosMuestreoListAux.add(datosMuestreo);
            }
            datosMuestreoList = datosMuestreoListAux;
        }

        return datosMuestreoList;
    }
}

package es.uca.cromuca.entities;

import es.uca.cromuca.entities.enums.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Entity
public class DatosMuestreo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String latitud = "", longitud = "", localidad = "", colector = "";
    @Lob
    private String observaciones;
    @ManyToOne
    private RegionBiogeografica regionBiogeografica;
    @ManyToOne
    private RegionMarina regionMarina;
    private LocalDate fechaCaptura;
    private Double profundidad;
    private String tipoOrganismo;
    @ManyToOne
    private TipoSustrato tipoSustrato;
    @Enumerated(EnumType.STRING)
    private ProcedenciaMaterial procedenciaMaterial;
    @ManyToOne
    private Especie especie;
    @ManyToOne
    private MetodoCaptura metodoCaptura;
    @ManyToOne
    private Pais pais;
    @ManyToOne
    private Habitat habitat;

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    public void setMetodoCaptura(MetodoCaptura metodoCaptura) {
        this.metodoCaptura = metodoCaptura;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRegionBiogeografica(RegionBiogeografica regionBiogeografica) {
        this.regionBiogeografica = regionBiogeografica;
    }

    public void setRegionMarina(RegionMarina regionMarina) {
        this.regionMarina = regionMarina;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Especie getEspecie() {
        return especie;
    }

    public Double getProfundidad() {
        return profundidad;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public LocalDate getFechaCaptura() {
        return fechaCaptura;
    }

    public MetodoCaptura getMetodoCaptura() {
        return metodoCaptura;
    }

    public Pais getPais() {
        return pais;
    }

    public ProcedenciaMaterial getProcedenciaMaterial() {
        return procedenciaMaterial;
    }

    public RegionBiogeografica getRegionBiogeografica() {
        return regionBiogeografica;
    }

    public RegionMarina getRegionMarina() {
        return regionMarina;
    }

    public String getColector() {
        return colector;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getLongitud() {
        return longitud;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getTipoOrganismo() {
        return tipoOrganismo;
    }

    public void setColector(String colector) {
        this.colector = colector;
    }

    public void setFechaCaptura(LocalDate fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setProcedenciaMaterial(ProcedenciaMaterial procedenciaMaterial) {
        this.procedenciaMaterial = procedenciaMaterial;
    }

    public void setProfundidad(Double profundidad) {
        this.profundidad = profundidad;
    }

    public TipoSustrato getTipoSustrato() {
        return tipoSustrato;
    }

    public void setTipoSustrato(TipoSustrato tipoSustrato) {
        this.tipoSustrato = tipoSustrato;
    }

    public void setTipoOrganismo(String tipoOrganismo) {
        this.tipoOrganismo = tipoOrganismo;
    }

    public static List<String> tiposOrganismosList() {
        List<String> stringList = new LinkedList<>();

        stringList.add("Adulto");
        stringList.add("Adulto + Larva");
        stringList.add("Larva");

        return stringList;
    }
}

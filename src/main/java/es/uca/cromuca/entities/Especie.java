package es.uca.cromuca.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Especie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Este campo es obligatorio")
    private String numeroCatalogo = "", numeroFrasco = "";
    private LocalDate fechaAlta;
    private String especie = "", autorAno = "";
    @ManyToOne
    private Genero genero;
    @ManyToOne
    private Familia familia;
    @ManyToOne
    private Phylum phylum;
    @ManyToOne
    private CategoriaTaxonomicaPpal categoriaTaxonomicaPpal;
    @OneToMany(mappedBy = "especie")
    private Set<DatosMuestreo> datosMuestreoSet;
    @OneToMany(mappedBy = "especie")
    private Set<Ejemplares> ejemplaresSet;
    @OneToMany(mappedBy = "especie")
    private Set<Conservacion> conservacionSet;
    @OneToMany(mappedBy = "especie")
    private Set<HistorialDeterminacion> historialDeterminacionSet;
    @OneToMany(mappedBy = "especie")
    private Set<Ubicacion> ubicacionSet;
    @OneToMany(mappedBy = "especie")
    private Set<Archivo> archivoSet;

    public Set<HistorialDeterminacion> getHistorialDeterminacionSet() {
        return historialDeterminacionSet;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Phylum getPhylum() {
        return phylum;
    }

    public void setPhylum(Phylum phylum) {
        this.phylum = phylum;
    }

    public CategoriaTaxonomicaPpal getCategoriaTaxonomicaPpal() {
        return categoriaTaxonomicaPpal;
    }

    public void setCategoriaTaxonomicaPpal(CategoriaTaxonomicaPpal categoriaTaxonomicaPpal) {
        this.categoriaTaxonomicaPpal = categoriaTaxonomicaPpal;
    }

    public void setHistorialDeterminacionSet(Set<HistorialDeterminacion> historialDeterminacionSet) {
        this.historialDeterminacionSet = historialDeterminacionSet;
    }

    public Set<Ubicacion> getUbicacionSet() {
        return ubicacionSet;
    }

    public void setUbicacionSet(Set<Ubicacion> ubicacionSet) {
        this.ubicacionSet = ubicacionSet;
    }

    public Set<Archivo> getArchivoSet() {
        return archivoSet;
    }

    public void setArchivoSet(Set<Archivo> archivoSet) {
        this.archivoSet = archivoSet;
    }

    public Set<Ejemplares> getEjemplaresSet() {
        return ejemplaresSet;
    }

    public void setEjemplaresSet(Set<Ejemplares> ejemplaresSet) {
        this.ejemplaresSet = ejemplaresSet;
    }

    public Set<Conservacion> getConservacionSet() {
        return conservacionSet;
    }

    public void setConservacionSet(Set<Conservacion> conservacionSet) {
        this.conservacionSet = conservacionSet;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroCatalogo() {
        return numeroCatalogo;
    }

    public void setDatosMuestreoSet(Set<DatosMuestreo> datosMuestreoSet) {
        this.datosMuestreoSet = datosMuestreoSet;
    }

    public Set<DatosMuestreo> getDatosMuestreoSet() {
        return datosMuestreoSet;
    }

    public String getNumeroFrasco() {
        return numeroFrasco;
    }

    public void setNumeroFrasco(String numeroFrasco) {
        this.numeroFrasco = numeroFrasco;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public String getEspecie() {
        return especie;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getAutorAno() {
        return autorAno;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setNumeroCatalogo(String numeroCatalogo) {
        this.numeroCatalogo = numeroCatalogo;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setAutorAno(String autorAno) {
        this.autorAno = autorAno;
    }
}

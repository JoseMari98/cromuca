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
    @OneToOne(mappedBy = "especie")
    private DatosMuestreo datosMuestreo;
    @OneToOne(mappedBy = "especie")
    private Ejemplares ejemplares;
    @OneToOne(mappedBy = "especie")
    private Conservacion conservacion;
    @OneToOne(mappedBy = "especie")
    private HistorialDeterminacion historialDeterminacion;
    @OneToOne(mappedBy = "especie")
    private Ubicacion ubicacion;
    @OneToMany(mappedBy = "especie")
    private Set<Archivo> archivoSet;

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

    public Set<Archivo> getArchivoSet() {
        return archivoSet;
    }

    public void setArchivoSet(Set<Archivo> archivoSet) {
        this.archivoSet = archivoSet;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroCatalogo() {
        return numeroCatalogo;
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

    public DatosMuestreo getDatosMuestreo() {
        return datosMuestreo;
    }

    public void setDatosMuestreo(DatosMuestreo datosMuestreo) {
        this.datosMuestreo = datosMuestreo;
    }

    public Ejemplares getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Ejemplares ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Conservacion getConservacion() {
        return conservacion;
    }

    public void setConservacion(Conservacion conservacion) {
        this.conservacion = conservacion;
    }

    public HistorialDeterminacion getHistorialDeterminacion() {
        return historialDeterminacion;
    }

    public void setHistorialDeterminacion(HistorialDeterminacion historialDeterminacion) {
        this.historialDeterminacion = historialDeterminacion;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
}

package es.uca.cromuca.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class HistorialDeterminacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String taxon = "", autor = "";
    @Lob
    private String referenciasBibliograficas, observaciones;
    private LocalDate fechaDeterminacion;
    @OneToOne
    private Especie especie;

    public Long getId() {
        return id;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public LocalDate getFechaDeterminacion() {
        return fechaDeterminacion;
    }

    public String getAutor() {
        return autor;
    }

    public String getReferenciasBibliograficas() {
        return referenciasBibliograficas;
    }

    public String getTaxon() {
        return taxon;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setFechaDeterminacion(LocalDate fechaDeterminacion) {
        this.fechaDeterminacion = fechaDeterminacion;
    }

    public void setReferenciasBibliograficas(String referenciasBibliograficas) {
        this.referenciasBibliograficas = referenciasBibliograficas;
    }

    public void setTaxon(String taxon) {
        this.taxon = taxon;
    }
}

package es.uca.cromuca.entities;

import es.uca.cromuca.entities.enums.ConservanteActual;
import es.uca.cromuca.entities.enums.Fijador;
import es.uca.cromuca.entities.enums.Narcotizacion;

import javax.persistence.*;

@Entity
public class Conservacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String incidencias = "", historial = "";
    private boolean estudiosGeneticos;
    @ManyToOne
    private Narcotizacion narcotizacion;
    @ManyToOne
    private Fijador fijador;
    @ManyToOne
    private ConservanteActual conservanteActual;

    public void setConservanteActual(ConservanteActual conservanteActual) {
        this.conservanteActual = conservanteActual;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNarcotizacion(Narcotizacion narcotizacion) {
        this.narcotizacion = narcotizacion;
    }

    public void setFijador(Fijador fijador) {
        this.fijador = fijador;
    }

    public boolean isEstudiosGeneticos() {
        return estudiosGeneticos;
    }

    public ConservanteActual getConservanteActual() {
        return conservanteActual;
    }

    public Fijador getFijador() {
        return fijador;
    }

    public Narcotizacion getNarcotizacion() {
        return narcotizacion;
    }

    public String getHistorial() {
        return historial;
    }

    public String getIncidencias() {
        return incidencias;
    }

    public void setEstudiosGeneticos(boolean estudiosGeneticos) {
        this.estudiosGeneticos = estudiosGeneticos;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public void setIncidencias(String incidencias) {
        this.incidencias = incidencias;
    }
}

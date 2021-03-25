package es.uca.cromuca.entities;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Conservacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String incidencias = "", historial = "";
    private boolean estudiosGeneticos;
    private String narcotizacion;
    private String fijador;
    private String conservanteActual;
    @OneToOne
    private Especie especie;

    public void setConservanteActual(String conservanteActual) {
        this.conservanteActual = conservanteActual;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNarcotizacion(String narcotizacion) {
        this.narcotizacion = narcotizacion;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public void setFijador(String fijador) {
        this.fijador = fijador;
    }

    public boolean isEstudiosGeneticos() {
        return estudiosGeneticos;
    }

    public String getConservanteActual() {
        return conservanteActual;
    }

    public String getFijador() {
        return fijador;
    }

    public String getNarcotizacion() {
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

    public static List<String> conservanteActualList() {
        List<String> stringList = new LinkedList<>();

        stringList.add("Congelación -20ºC");
        stringList.add("Congelación -80ºC");
        stringList.add("Etanol 100%");
        stringList.add("Etanol 96%");
        stringList.add("Etanol 90%");
        stringList.add("Etanol 70%");
        stringList.add("Formol 4%");
        stringList.add("Glicerina");
        stringList.add("Gluteraldehido");
        stringList.add("Nitrógeno líquido");
        stringList.add("Seco");

        return stringList;
    }

    public static List<String> narcotizacionList() {
        List<String> stringList = new LinkedList<>();

        stringList.add("Congelación");
        stringList.add("Etanol");
        stringList.add("Mentol");
        stringList.add("Ninguna");

        return stringList;
    }

    public static List<String> fijadorList() {
        List<String> stringList = new LinkedList<>();

        stringList.add("Congelación -20ºC");
        stringList.add("Congelación -80ºC");
        stringList.add("Etanol 100%");
        stringList.add("Etanol 96%");
        stringList.add("Etanol 90%");
        stringList.add("Etanol 70%");
        stringList.add("Formol 4%");
        stringList.add("Nitrógeno líquido");

        return stringList;
    }
}

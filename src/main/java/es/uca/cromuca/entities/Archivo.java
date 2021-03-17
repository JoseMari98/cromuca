package es.uca.cromuca.entities;

import es.uca.cromuca.entities.enums.FormatoMultimedia;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Archivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String autor = "", link = "";
    @Lob
    private String observaciones = "";
    private LocalDate fecha;
    @Enumerated(EnumType.STRING)
    private FormatoMultimedia formatoMultimedia;
    @ManyToOne
    private Especie especie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public FormatoMultimedia getFormatoMultimedia() {
        return formatoMultimedia;
    }

    public void setFormatoMultimedia(FormatoMultimedia formatoMultimedia) {
        this.formatoMultimedia = formatoMultimedia;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }
}

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
    private String especie = "", autorAno = "", numeroCatalogo = "";
    private LocalDate fechaAlta;
    @ManyToOne
    private Genero genero;
    @OneToMany(mappedBy = "id")
    private Set<DatosMuestreo> datosMuestreoSet;

    public Long getId() {
        return id;
    }

    public String getNumeroCatalogo() {
        return numeroCatalogo;
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

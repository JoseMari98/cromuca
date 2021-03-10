package es.uca.cromuca.entities.enums;

import es.uca.cromuca.entities.Conservacion;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class Narcotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Este campo es obligatorio")
    private String narcotizacion = "";
    @OneToMany(mappedBy = "id")
    private Set<Conservacion> conservacionSet;

    public void setConservacionSet(Set<Conservacion> conservacionSet) {
        this.conservacionSet = conservacionSet;
    }

    public Set<Conservacion> getConservacionSet() {
        return conservacionSet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNarcotizacion() {
        return narcotizacion;
    }

    public void setNarcotizacion(String narcotizacion) {
        this.narcotizacion = narcotizacion;
    }
}

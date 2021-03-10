package es.uca.cromuca.entities.enums;

import es.uca.cromuca.entities.Conservacion;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class ConservanteActual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Este campo es obligatorio")
    private String conservanteActual = "";
    @OneToMany(mappedBy = "id")
    private Set<Conservacion> conservacionSet;

    public Long getId() {
        return id;
    }

    public Set<Conservacion> getConservacionSet() {
        return conservacionSet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setConservacionSet(Set<Conservacion> conservacionSet) {
        this.conservacionSet = conservacionSet;
    }

    public String getConservanteActual() {
        return conservanteActual;
    }

    public void setConservanteActual(String conservanteActual) {
        this.conservanteActual = conservanteActual;
    }
}

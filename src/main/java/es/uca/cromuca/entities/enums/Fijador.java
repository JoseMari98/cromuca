package es.uca.cromuca.entities.enums;

import es.uca.cromuca.entities.Conservacion;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class Fijador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Este campo es obligatorio")
    private String fijador = "";
    @OneToMany(mappedBy = "id")
    private Set<Conservacion> conservacionSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFijador() {
        return fijador;
    }

    public Set<Conservacion> getConservacionSet() {
        return conservacionSet;
    }

    public void setFijador(String fijador) {
        this.fijador = fijador;
    }

    public void setConservacionSet(Set<Conservacion> conservacionSet) {
        this.conservacionSet = conservacionSet;
    }
}

package es.uca.cromuca.entities.enums;

import es.uca.cromuca.entities.Ejemplares;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class CaracteristicasTipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Este campo es obligatorio")
    private String caracteristicasTipo = "";
    @OneToMany(mappedBy = "id")
    private Set<Ejemplares> ejemplaresSet;

    public void setEjemplaresSet(Set<Ejemplares> ejemplaresSet) {
        this.ejemplaresSet = ejemplaresSet;
    }

    public Set<Ejemplares> getEjemplaresSet() {
        return ejemplaresSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaracteristicasTipo() {
        return caracteristicasTipo;
    }

    public void setCaracteristicasTipo(String caracteristicasTipo) {
        this.caracteristicasTipo = caracteristicasTipo;
    }
}

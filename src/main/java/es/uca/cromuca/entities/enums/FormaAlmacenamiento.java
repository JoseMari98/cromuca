package es.uca.cromuca.entities.enums;

import es.uca.cromuca.entities.Ejemplares;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class FormaAlmacenamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Este campo es obligatorio")
    private String formaAlmacenamiento = "";
    @OneToMany(mappedBy = "id")
    private Set<Ejemplares> ejemplaresSet;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFormaAlmacenamiento() {
        return formaAlmacenamiento;
    }

    public void setFormaAlmacenamiento(String formaAlmacenamiento) {
        this.formaAlmacenamiento = formaAlmacenamiento;
    }

    public Set<Ejemplares> getEjemplaresSet() {
        return ejemplaresSet;
    }

    public void setEjemplaresSet(Set<Ejemplares> ejemplaresSet) {
        this.ejemplaresSet = ejemplaresSet;
    }
}

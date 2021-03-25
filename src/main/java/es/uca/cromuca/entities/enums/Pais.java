package es.uca.cromuca.entities.enums;

import es.uca.cromuca.entities.DatosMuestreo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Este campo es obligatorio")
    private String pais = "";
    @OneToMany(mappedBy = "pais")
    private Set<DatosMuestreo> datosMuestreoSet;

    public void setId(Long id) {
        this.id = id;
    }

    public Set<DatosMuestreo> getDatosMuestreoSet() {
        return datosMuestreoSet;
    }

    public void setDatosMuestreoSet(Set<DatosMuestreo> datosMuestreoSet) {
        this.datosMuestreoSet = datosMuestreoSet;
    }

    public Long getId() {
        return id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}

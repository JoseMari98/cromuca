package es.uca.cromuca.entities.enums;

import es.uca.cromuca.entities.DatosMuestreo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class MetodoCaptura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Este campo es obligatorio")
    private String metodoCaptura = "";
    @OneToMany(mappedBy = "metodoCaptura")
    private Set<DatosMuestreo> datosMuestreoSet;

    public Long getId() {
        return id;
    }

    public void setDatosMuestreoSet(Set<DatosMuestreo> datosMuestreoSet) {
        this.datosMuestreoSet = datosMuestreoSet;
    }

    public Set<DatosMuestreo> getDatosMuestreoSet() {
        return datosMuestreoSet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetodoCaptura() {
        return metodoCaptura;
    }

    public void setMetodoCaptura(String metodoCaptura) {
        this.metodoCaptura = metodoCaptura;
    }
}

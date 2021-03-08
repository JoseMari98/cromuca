package es.uca.cromuca.entities.enums;

import es.uca.cromuca.entities.DatosMuestreo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class RegionMarina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Este campo es obligatorio")
    private String regionMarina = "";
    @OneToMany(mappedBy = "id")
    private Set<DatosMuestreo> datosMuestreoSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegionMarina() {
        return regionMarina;
    }

    public Set<DatosMuestreo> getDatosMuestreoSet() {
        return datosMuestreoSet;
    }

    public void setRegionMarina(String regionMarina) {
        this.regionMarina = regionMarina;
    }

    public void setDatosMuestreoSet(Set<DatosMuestreo> datosMuestreoSet) {
        this.datosMuestreoSet = datosMuestreoSet;
    }
}

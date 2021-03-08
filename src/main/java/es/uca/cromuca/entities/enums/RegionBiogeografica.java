package es.uca.cromuca.entities.enums;

import es.uca.cromuca.entities.DatosMuestreo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class RegionBiogeografica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Este campo es obligatorio")
    private String regionBiogeografica = "";
    @OneToMany(mappedBy = "id")
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

    public String getRegionBiogeografica() {
        return regionBiogeografica;
    }

    public void setRegionBiogeografica(String regionBiogeografica) {
        this.regionBiogeografica = regionBiogeografica;
    }
}

package es.uca.cromuca.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class Phylum {
    @Id //esto sirve para decir cual es el id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Este campo es obligatorio")
    private String phylum = "";
    @OneToMany(mappedBy = "phylum")
    private Set<CategoriaTaxonomicaPpal> categoriaTaxonomicaPpalSet;
    @OneToMany(mappedBy = "especie")
    private Set<Especie> especieSete;

    public Long getId() {
        return id;
    }

    public String getPhylum() {
        return phylum;
    }

    public Set<Especie> getEspecieSete() {
        return especieSete;
    }

    public void setEspecieSete(Set<Especie> especieSete) {
        this.especieSete = especieSete;
    }

    public Set<CategoriaTaxonomicaPpal> getCategoriaTaxonomicaPpalSet() {
        return categoriaTaxonomicaPpalSet;
    }

    public void setCategoriaTaxonomicaPpalSet(Set<CategoriaTaxonomicaPpal> categoriaTaxonomicaPpalSet) {
        this.categoriaTaxonomicaPpalSet = categoriaTaxonomicaPpalSet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPhylum(String phylum) {
        this.phylum = phylum;
    }
}

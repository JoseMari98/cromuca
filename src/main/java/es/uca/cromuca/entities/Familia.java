package es.uca.cromuca.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class Familia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Este campo es obligatorio")
    private String familia = "";
    @OneToMany(mappedBy = "genero")
    private Set<Genero> generoSet;
    @ManyToOne
    private CategoriaTaxonomicaPpal categoriaTaxonomicaPpal;

    public Long getId() {
        return id;
    }

    public String getFamilia() {
        return familia;
    }

    public CategoriaTaxonomicaPpal getCategoriaTaxonomicaPpal() {
        return categoriaTaxonomicaPpal;
    }

    public Set<Genero> getGeneroSet() {
        return generoSet;
    }

    public void setCategoriaTaxonomicaPpal(CategoriaTaxonomicaPpal categoriaTaxonomicaPpal) {
        this.categoriaTaxonomicaPpal = categoriaTaxonomicaPpal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public void setGeneroSet(Set<Genero> generoSet) {
        this.generoSet = generoSet;
    }
}

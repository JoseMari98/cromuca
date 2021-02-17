package es.uca.cromuca.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class CategoriaTaxonomicaPpal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Este campo es obligatorio")
    private String categoriaTaxonomicaPpal = "";
    @OneToMany(mappedBy = "familia")
    private Set<Familia> familiaSet;
    @ManyToOne
    private Phylum phylum;

    public Long getId() {
        return id;
    }

    public String getCategoriaTaxonomicaPpal() {
        return categoriaTaxonomicaPpal;
    }

    public Phylum getPhylum() {
        return phylum;
    }

    public Set<Familia> getFamiliaSet() {
        return familiaSet;
    }

    public void setPhylum(Phylum phylum) {
        this.phylum = phylum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoriaTaxonomicaPpal(String categoriaTaxonomicaPpal) {
        this.categoriaTaxonomicaPpal = categoriaTaxonomicaPpal;
    }

    public void setFamiliaSet(Set<Familia> familiaSet) {
        this.familiaSet = familiaSet;
    }
}

package es.uca.cromuca.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Este campo es obligatorio")
    private String genero = "";
    @OneToMany(mappedBy = "especie")
    private Set<Especie> especieSete;
    @ManyToOne
    private Familia familia;

    public Long getId() {
        return id;
    }

    public Familia getFamilia() {
        return familia;
    }

    public String getGenero() {
        return genero;
    }

    public Set<Especie> getEspecieSete() {
        return especieSete;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEspecieSete(Set<Especie> especieSete) {
        this.especieSete = especieSete;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}

package es.uca.cromuca.entities;

import es.uca.cromuca.entities.enums.Estatus;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int armario = 0, estante = 0, cajon = 0;
    @Enumerated(EnumType.STRING)
    private Estatus estatus;
    @OneToMany(mappedBy = "ubicacion")
    private Set<Prestamo> prestamoSet;

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Prestamo> getPrestamosSet() {
        return prestamoSet;
    }

    public void setPrestamosSet(Set<Prestamo> prestamoSet) {
        this.prestamoSet = prestamoSet;
    }

    public Long getId() {
        return id;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public int getArmario() {
        return armario;
    }

    public int getCajon() {
        return cajon;
    }

    public int getEstante() {
        return estante;
    }

    public void setArmario(int armario) {
        this.armario = armario;
    }

    public void setCajon(int cajon) {
        this.cajon = cajon;
    }

    public void setEstante(int estante) {
        this.estante = estante;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }
}

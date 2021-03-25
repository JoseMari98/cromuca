package es.uca.cromuca.entities;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int armario = 0, estante = 0, cajon = 0;
    private String estatus;
    @OneToMany(mappedBy = "ubicacion")
    private Set<Prestamo> prestamoSet;
    @OneToOne
    private Especie especie;

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Prestamo> getPrestamoSet() {
        return prestamoSet;
    }

    public void setPrestamoSet(Set<Prestamo> prestamoSet) {
        this.prestamoSet = prestamoSet;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
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

    public String getEstatus() {
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

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public static List<String> estatusList() {
        List<String> stringList = new LinkedList<>();

        stringList.add("Disponible");
        stringList.add("Prestado(Todo)");
        stringList.add("Prestado(Parte)");
        stringList.add("Donado");
        stringList.add("Perdido");

        return stringList;
    }
}

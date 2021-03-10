package es.uca.cromuca.entities;

import es.uca.cromuca.entities.enums.CaracteristicasTipo;
import es.uca.cromuca.entities.enums.FormaAlmacenamiento;
import es.uca.cromuca.entities.enums.Formato;

import javax.persistence.*;

@Entity
public class Ejemplares {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int machos = 0, hembrasAdultas = 0, hembrasOvig = 0, hermafroditas = 0, sinSexo = 0, juveniles = 0, frascosLote = 0;
    private boolean huevos, larvas;
    @Enumerated(EnumType.STRING)
    private Formato tipoOrganismo;
    @ManyToOne
    private CaracteristicasTipo caracteristicasTipo;
    @ManyToOne
    private FormaAlmacenamiento formaAlmacenamiento;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCaracteristicasTipo(CaracteristicasTipo caracteristicasTipo) {
        this.caracteristicasTipo = caracteristicasTipo;
    }

    public Long getId() {
        return id;
    }

    public void setFormaAlmacenamiento(FormaAlmacenamiento formaAlmacenamiento) {
        this.formaAlmacenamiento = formaAlmacenamiento;
    }

    public void setTipoOrganismo(Formato tipoOrganismo) {
        this.tipoOrganismo = tipoOrganismo;
    }

    public boolean isHuevos() {
        return huevos;
    }

    public boolean isLarvas() {
        return larvas;
    }

    public CaracteristicasTipo getCaracteristicasTipo() {
        return caracteristicasTipo;
    }

    public FormaAlmacenamiento getFormaAlmacenamiento() {
        return formaAlmacenamiento;
    }

    public Formato getTipoOrganismo() {
        return tipoOrganismo;
    }

    public int getFrascosLote() {
        return frascosLote;
    }

    public int getHembrasAdultas() {
        return hembrasAdultas;
    }

    public int getHembrasOvig() {
        return hembrasOvig;
    }

    public int getHermafroditas() {
        return hermafroditas;
    }

    public int getJuveniles() {
        return juveniles;
    }

    public int getMachos() {
        return machos;
    }

    public int getSinSexo() {
        return sinSexo;
    }

    public void setFrascosLote(int frascosLote) {
        this.frascosLote = frascosLote;
    }

    public void setHembrasAdultas(int hembrasAdultas) {
        this.hembrasAdultas = hembrasAdultas;
    }

    public void setHembrasOvig(int hembrasOvig) {
        this.hembrasOvig = hembrasOvig;
    }

    public void setHermafroditas(int hermafroditas) {
        this.hermafroditas = hermafroditas;
    }

    public void setHuevos(boolean huevos) {
        this.huevos = huevos;
    }

    public void setJuveniles(int juveniles) {
        this.juveniles = juveniles;
    }

    public void setLarvas(boolean larvas) {
        this.larvas = larvas;
    }

    public void setMachos(int machos) {
        this.machos = machos;
    }

    public void setSinSexo(int sinSexo) {
        this.sinSexo = sinSexo;
    }
}

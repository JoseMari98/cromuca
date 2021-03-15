package es.uca.cromuca.entities;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Ejemplares {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int machos = 0, hembrasAdultas = 0, hembrasOvig = 0, hermafroditas = 0, sinSexo = 0, juveniles = 0, frascosLote = 0;
    private boolean huevos, larvas;
    private String formato;
    private String caracteristicasTipo;
    private String formaAlmacenamiento;
    @ManyToOne
    private Especie especie;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCaracteristicasTipo(String caracteristicasTipo) {
        this.caracteristicasTipo = caracteristicasTipo;
    }

    public Long getId() {
        return id;
    }

    public void setFormaAlmacenamiento(String formaAlmacenamiento) {
        this.formaAlmacenamiento = formaAlmacenamiento;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public boolean isHuevos() {
        return huevos;
    }

    public boolean isLarvas() {
        return larvas;
    }

    public String getCaracteristicasTipo() {
        return caracteristicasTipo;
    }

    public String getFormaAlmacenamiento() {
        return formaAlmacenamiento;
    }

    public String getFormato() {
        return formato;
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

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public static List<String> formaAlmacenamientoList() {
        List<String> stringList = new LinkedList<>();

        stringList.add("Bolsa");
        stringList.add("Caja");
        stringList.add("Contenedor");
        stringList.add("Frasco");

        return stringList;
    }

    public static List<String> caracteristicaTipoList() {
        List<String> stringList = new LinkedList<>();

        stringList.add("Alotipo");
        stringList.add("Alotipo + Paratipo");
        stringList.add("Apantotipo");
        stringList.add("Holotipo");
        stringList.add("Holotipo + Alotipo");
        stringList.add("Holotipo + Alotipo + Paratipo");
        stringList.add("Holotipo + Paratipo");
        stringList.add("Isolectotipo");
        stringList.add("Isotipo");
        stringList.add("Lectotipo");
        stringList.add("Lectotipo + Paralectotipo");
        stringList.add("Neoparatipo");
        stringList.add("Neotipo");
        stringList.add("Paralectotipo");
        stringList.add("Paraneotipo");
        stringList.add("Paratipo");

        return stringList;
    }

    public static List<String> formatoList() {
        List<String> stringList = new LinkedList<>();

        stringList.add("Animal Completo");
        stringList.add("Animal Diseccionado");
        stringList.add("Parte de animal");
        stringList.add("Colonia");
        stringList.add("Preparación microscópica");

        return stringList;
    }
}

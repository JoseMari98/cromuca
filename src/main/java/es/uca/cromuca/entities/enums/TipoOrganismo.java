package es.uca.cromuca.entities.enums;

import java.util.LinkedList;
import java.util.List;

public class TipoOrganismo {
    private String tipoOrganismo = "";

    public TipoOrganismo(String nombre) {
        this.tipoOrganismo = nombre;
    }

    public void setTipoOrganismo(String tipoOrganismo) {
        this.tipoOrganismo = tipoOrganismo;
    }

    public String getTipoOrganismo() {
        return tipoOrganismo;
    }

    public static List<TipoOrganismo> tiposOrganismosList() {
        List<TipoOrganismo> stringList = new LinkedList<>();

        stringList.add(new TipoOrganismo("Adulto"));
        stringList.add(new TipoOrganismo("Adulto + Larva"));
        stringList.add(new TipoOrganismo("Larva"));

        return stringList;
    }
}

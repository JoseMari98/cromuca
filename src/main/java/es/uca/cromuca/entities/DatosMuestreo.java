package es.uca.cromuca.entities;

import es.uca.cromuca.entities.enums.MetodoCaptura;
import es.uca.cromuca.entities.enums.Pais;
import es.uca.cromuca.entities.enums.ProcedenciaMaterial;
import es.uca.cromuca.entities.enums.TipoSustrato;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Entity
public class DatosMuestreo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String latitud = "", longitud = "", localidad = "", colector = "";
    @Lob
    private String observaciones;
    private String regionBiogeografica;
    private String regionMarina;
    private LocalDate fechaCaptura;
    private Double profundidad;
    private String tipoOrganismo;
    @ManyToOne
    private TipoSustrato tipoSustrato;
    @Enumerated(EnumType.STRING)
    private ProcedenciaMaterial procedenciaMaterial;
    @OneToOne
    private Especie especie;
    @ManyToOne
    private MetodoCaptura metodoCaptura;
    @ManyToOne
    private Pais pais;
    private String habitat;

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public void setMetodoCaptura(MetodoCaptura metodoCaptura) {
        this.metodoCaptura = metodoCaptura;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRegionBiogeografica(String regionBiogeografica) {
        this.regionBiogeografica = regionBiogeografica;
    }

    public void setRegionMarina(String regionMarina) {
        this.regionMarina = regionMarina;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Especie getEspecie() {
        return especie;
    }

    public Double getProfundidad() {
        return profundidad;
    }

    public String getHabitat() {
        return habitat;
    }

    public LocalDate getFechaCaptura() {
        return fechaCaptura;
    }

    public MetodoCaptura getMetodoCaptura() {
        return metodoCaptura;
    }

    public Pais getPais() {
        return pais;
    }

    public ProcedenciaMaterial getProcedenciaMaterial() {
        return procedenciaMaterial;
    }

    public String getRegionBiogeografica() {
        return regionBiogeografica;
    }

    public String getRegionMarina() {
        return regionMarina;
    }

    public String getColector() {
        return colector;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getLongitud() {
        return longitud;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getTipoOrganismo() {
        return tipoOrganismo;
    }

    public void setColector(String colector) {
        this.colector = colector;
    }

    public void setFechaCaptura(LocalDate fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setProcedenciaMaterial(ProcedenciaMaterial procedenciaMaterial) {
        this.procedenciaMaterial = procedenciaMaterial;
    }

    public void setProfundidad(Double profundidad) {
        this.profundidad = profundidad;
    }

    public TipoSustrato getTipoSustrato() {
        return tipoSustrato;
    }

    public void setTipoSustrato(TipoSustrato tipoSustrato) {
        this.tipoSustrato = tipoSustrato;
    }

    public void setTipoOrganismo(String tipoOrganismo) {
        this.tipoOrganismo = tipoOrganismo;
    }

    public static List<String> tiposOrganismosList() {
        List<String> stringList = new LinkedList<>();

        stringList.add("Adulto");
        stringList.add("Adulto + Larva");
        stringList.add("Larva");

        return stringList;
    }

    public static List<String> regionBiogeograficaList() {
        List<String> stringList = new LinkedList<>();

        stringList.add("1. Región Antártica");
        stringList.add("2. Región Ártica");
        stringList.add("3. Mediterráneo");
        stringList.add("4. Atlántico Noroeste");
        stringList.add("5. Atlántico Noreste");
        stringList.add("6. Bático");
        stringList.add("7. Caribe");
        stringList.add("8. África Oeste");
        stringList.add("9. Atlántico Sur");
        stringList.add("10. Índico Central");
        stringList.add("11. Mar arábico");
        stringList.add("12. África Este");
        stringList.add("13. Mar Asiático Este");
        stringList.add("14. Pacífico Sur");
        stringList.add("15. Pacífico Norte Este");
        stringList.add("16. Pacífico Norte Oeste");
        stringList.add("17. Pacífico Sur Este");

        return stringList;
    }

    public static List<String> regionMarinaList() {
        List<String> stringList = new LinkedList<>();

        stringList.add("1. Bentos Supramareal");
        stringList.add("2. Bentos Intermareal");
        stringList.add("3. Bentos Submareal");
        stringList.add("4. Bentos Plataforma continental");
        stringList.add("5. Bentos Talud continental");
        stringList.add("6. Bentos Llanura abisal");
        stringList.add("7. Bentos Fosa Hadal");
        stringList.add("8. Nerítica");
        stringList.add("9. Epipelágica Oceánica");
        stringList.add("10. Mesopelágica");
        stringList.add("11. Batipelágica");
        stringList.add("12. Abisal");
        stringList.add("13. Hadal");

        return stringList;
    }

    public static List<String> habitatList() {
        List<String> stringList = new LinkedList<>();

        stringList.add("Algas");
        stringList.add("Algas calcareas");
        stringList.add("Arrecife de coral");
        stringList.add("Comensal");
        stringList.add("Cuevas");
        stringList.add("Estuario");
        stringList.add("Fanerógamas");
        stringList.add("Intermareal rocoso");
        stringList.add("Laguna costera");
        stringList.add("Manglar");
        stringList.add("Roquedo");
        stringList.add("Terrestre");

        return stringList;
    }
}

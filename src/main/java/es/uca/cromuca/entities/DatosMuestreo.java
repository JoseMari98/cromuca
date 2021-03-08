package es.uca.cromuca.entities;

import es.uca.cromuca.entities.enums.ProcedenciaMaterial;
import es.uca.cromuca.entities.enums.RegionBiogeografica;
import es.uca.cromuca.entities.enums.RegionMarina;
import es.uca.cromuca.entities.enums.TipoOrganismo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
public class DatosMuestreo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Este campo es obligatorio")
    private String latitud = "", longitud = "", localidad = "", colector = "";
    @Lob
    private String observaciones;
    @ManyToOne
    private RegionBiogeografica regionBiogeografica;
    @ManyToOne
    private RegionMarina regionMarina;
    private LocalDate fechaCaptura;
    private float profundidad;
    @Enumerated(EnumType.STRING)
    private TipoOrganismo tipoOrganismo;
    @Enumerated(EnumType.STRING)
    private ProcedenciaMaterial procedenciaMaterial;
    @ManyToOne
    private Especie especie;
}

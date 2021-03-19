package es.uca.cromuca.forms;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import es.uca.cromuca.entities.DatosMuestreo;
import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.entities.Usuario;
import es.uca.cromuca.entities.enums.MetodoCaptura;
import es.uca.cromuca.entities.enums.Pais;
import es.uca.cromuca.entities.enums.ProcedenciaMaterial;
import es.uca.cromuca.entities.enums.TipoSustrato;
import es.uca.cromuca.services.DatosMuestreoService;
import es.uca.cromuca.services.EspecieService;
import es.uca.cromuca.services.enums.MetodoCapturaService;
import es.uca.cromuca.services.enums.PaisService;
import es.uca.cromuca.services.enums.TipoSustratoService;
import es.uca.cromuca.views.DatosMuestreoView;

public class DatosMuestreoForm extends FormLayout {
    public Button comprobar = new Button("Buscar");
    private Button addThingsPais = new Button("+");
    private Button addThingsTipoSustrato = new Button("+");
    private Button addThingsMetodoCaptura = new Button("+");
    public TextField numeroCatalogo = new TextField("Num. catálogo");
    public TextField numeroFrasco = new TextField();
    private TextField localidad = new TextField("Localidad");
    private TextField latitud = new TextField("Latitud");
    private TextField longitud = new TextField("Latitud");
    private TextField colector = new TextField("Colector/Org/Proy");
    private TextArea observaciones = new TextArea("Observaciones");
    private NumberField profundidad = new NumberField("Profundidad(m)");
    private DatePicker fechaCaptura = new DatePicker("Fecha captura");
    private ComboBox<String> tipoOrganismo = new ComboBox<>("Tipo organismo");
    private ComboBox<Pais> pais = new ComboBox<>("Pais");
    private ComboBox<MetodoCaptura> metodoCaptura = new ComboBox<>("Metodo Captura");
    private ComboBox<TipoSustrato> tipoSustrato = new ComboBox<>("Tipo sustrato");
    private ComboBox<String> regionBiogeografica = new ComboBox<>("Región Biogeografica");
    private ComboBox<String> regionMarina = new ComboBox<>("Región marina");
    private ComboBox<String> habitat = new ComboBox<>("Habitat/Comunidad");
    private ComboBox<ProcedenciaMaterial> procedenciaMaterial = new ComboBox<>("Procedencia material");
    private EspecieService especieService;
    private TipoSustratoService tipoSustratoService;
    private PaisService paisService;
    private MetodoCapturaService metodoCapturaService;
    private DatosMuestreoService datosMuestreoService;
    private DatosMuestreoView datosMuestreoView;
    public Especie especieCreada;
    private Binder<DatosMuestreo> binder = new Binder<>(DatosMuestreo.class);
    private Button save = new Button("Continuar");
    private DatosMuestreo datosMuestreo = null;

    public DatosMuestreoForm(DatosMuestreoView datosMuestreoView, EspecieService especieService, DatosMuestreoService datosMuestreoService,
                             TipoSustratoService tipoSustratoService, PaisService paisService, MetodoCapturaService metodoCapturaService) {
        this.datosMuestreoService = datosMuestreoService;
        this.especieService = especieService;
        this.tipoSustratoService = tipoSustratoService;
        this.paisService = paisService;
        this.metodoCapturaService = metodoCapturaService;
        this.datosMuestreoView = datosMuestreoView;

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        comprobar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        tipoSustrato.setItems(tipoSustratoService.findAll());
        tipoSustrato.setItemLabelGenerator(TipoSustrato::getTipoSustrato);

        metodoCaptura.setItems(metodoCapturaService.findAll());
        metodoCaptura.setItemLabelGenerator(MetodoCaptura::getMetodoCaptura);

        pais.setItems(paisService.findAll());
        pais.setItemLabelGenerator(Pais::getPais);

        tipoOrganismo.setItems(DatosMuestreo.tiposOrganismosList());

        procedenciaMaterial.setItems(ProcedenciaMaterial.values());

        regionBiogeografica.setItems(DatosMuestreo.regionBiogeograficaList());

        regionMarina.setItems(DatosMuestreo.regionMarinaList());

        habitat.setItems(DatosMuestreo.habitatList());

        binder.bindInstanceFields(this);

        addThingsPais.addClickListener(e -> {
            UI.getCurrent().navigate("GestionPais");
        });
        addThingsTipoSustrato.addClickListener(e -> {
            UI.getCurrent().navigate("GestionTipoSustrato");
        });
        addThingsMetodoCaptura.addClickListener(e -> {
            UI.getCurrent().navigate("GestionMetodoCaptura");
        });

        if (UI.getCurrent().getSession().getAttribute(Usuario.class) == null) {
            addThingsPais.setEnabled(false);
            addThingsMetodoCaptura.setEnabled(false);
            addThingsTipoSustrato.setEnabled(false);
            save.setEnabled(false);
        }

        comprobar.addClickListener(e -> {
            if (especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue()) != null) {
                especieCreada = especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue());
                datosMuestreo = datosMuestreoService.findByEspecie(especieCreada);
                if (datosMuestreo != null) {
                    setDatosMuestreo(datosMuestreo);
                    Notification.show("Datos cargados", 3000, Notification.Position.MIDDLE);
                } else {
                    Notification.show("No hay datos de muestreo", 3000, Notification.Position.MIDDLE);
                    datosMuestreo = new DatosMuestreo();
                    setDatosMuestreo(datosMuestreo);
                }
                datosMuestreo.setEspecie(especieCreada);
            } else {
                Notification.show("Numero no existe", 3000, Notification.Position.MIDDLE);
                numeroCatalogo.setValue("");
                numeroFrasco.setValue("");
                datosMuestreo = null;
            }
        });
        profundidad.setMin(0);
        observaciones.getStyle().set("minHeight", "150px");
        HorizontalLayout tipoSustratoLay = new HorizontalLayout(tipoSustrato, addThingsTipoSustrato);
        tipoSustratoLay.setAlignItems(FlexComponent.Alignment.BASELINE);
        HorizontalLayout metodoCapturaLay = new HorizontalLayout(metodoCaptura, addThingsMetodoCaptura);
        metodoCapturaLay.setAlignItems(FlexComponent.Alignment.BASELINE);
        HorizontalLayout paisLay = new HorizontalLayout(pais, addThingsPais);
        paisLay.setAlignItems(FlexComponent.Alignment.BASELINE);
        VerticalLayout izq = new VerticalLayout(tipoOrganismo, fechaCaptura, profundidad, paisLay, localidad, latitud, longitud, metodoCapturaLay);
        VerticalLayout dcha = new VerticalLayout(regionBiogeografica, regionMarina, habitat, tipoSustratoLay, procedenciaMaterial, colector);

        add(izq, dcha, observaciones, save);
        save.addClickListener(event -> save());
    }

    public void setDatosMuestreo(DatosMuestreo datosMuestreo) {
        binder.setBean(datosMuestreo);
    }


    public void save() {
        if (datosMuestreo != null && especieCreada != null) {
            datosMuestreo = binder.getBean();
            datosMuestreo.setEspecie(especieCreada);
            if (profundidad.getValue() == null)
                profundidad.setValue(0.0);
            datosMuestreoService.guardar(datosMuestreo);
            Notification.show("Valores guardados", 3000, Notification.Position.MIDDLE);
        } else
            Notification.show("No hay especie", 3000, Notification.Position.MIDDLE);
    }
}
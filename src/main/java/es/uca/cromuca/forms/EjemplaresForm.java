package es.uca.cromuca.forms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import es.uca.cromuca.entities.Ejemplares;
import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.services.EjemplaresService;
import es.uca.cromuca.services.EspecieService;
import es.uca.cromuca.views.EjemplaresView;

public class EjemplaresForm extends FormLayout {
    private Button comprobar = new Button("Buscar");
    private TextField numeroCatalogo = new TextField("Num. catálogo");
    private TextField numeroFrasco = new TextField();
    private IntegerField frascosLote = new IntegerField("Numero de frascos por lote");
    private IntegerField machos = new IntegerField("Num. machos adultos");
    private IntegerField hembrasAdultas = new IntegerField("Num. hembras adultas");
    private IntegerField hembrasOvig = new IntegerField("Num. hembras ovíg");
    private IntegerField hermafroditas = new IntegerField("Num. hermafroditas");
    private IntegerField sinSexo = new IntegerField("Num. ejemplares sin sexar");
    private IntegerField juveniles = new IntegerField("Num. juveniles");
    private Checkbox huevos = new Checkbox("Huevos");
    private Checkbox larvas = new Checkbox("Larvas");
    private ComboBox<String> formaAlmacenamiento = new ComboBox<>("Forma de almacenamiento");
    private ComboBox<String> caracteristicasTipo = new ComboBox<>("Caracteristicas del tipo");
    private RadioButtonGroup<String> formato = new RadioButtonGroup<>();
    private EspecieService especieService;
    private EjemplaresService ejemplaresService;
    private EjemplaresForm ejemplaresForm;
    private EjemplaresView ejemplaresView;
    private Especie especieCreada;
    private Binder<Ejemplares> binder = new Binder<>(Ejemplares.class);
    private Button save = new Button("Continuar");
    private Ejemplares ejemplares = null;

    public EjemplaresForm(EjemplaresView ejemplaresView, EspecieService especieService, EjemplaresService ejemplaresService) {
        this.ejemplaresView = ejemplaresView;
        this.especieService = especieService;
        this.ejemplaresService = ejemplaresService;

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        comprobar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        caracteristicasTipo.setRequired(true);
        caracteristicasTipo.setItems(Ejemplares.caracteristicaTipoList());

        formaAlmacenamiento.setRequired(true);
        formaAlmacenamiento.setItems(Ejemplares.formaAlmacenamientoList());

        binder.bindInstanceFields(this);

        comprobar.addClickListener(e -> {
            if (especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue()) != null) {
                especieCreada = especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue());
                ejemplares = ejemplaresService.findByEspecie(especieCreada);
                if (ejemplares != null) {
                    setEjemplares(ejemplares);
                    Notification.show("Datos cargados", 3000, Notification.Position.MIDDLE);
                } else {
                    Notification.show("No hay datos de ejemplares", 3000, Notification.Position.MIDDLE);
                    ejemplares = new Ejemplares();
                    setEjemplares(ejemplares);
                }
                ejemplares.setEspecie(especieCreada);
            } else {
                Notification.show("Numero no existe", 3000, Notification.Position.MIDDLE);
                numeroCatalogo.setValue("");
                numeroFrasco.setValue("");
                ejemplares = null;
            }
        });
        hermafroditas.setMin(0);
        frascosLote.setMin(0);
        machos.setMin(0);
        hermafroditas.setMin(0);
        hembrasAdultas.setMin(0);
        hembrasOvig.setMin(0);
        sinSexo.setMin(0);
        juveniles.setMin(0);

        hermafroditas.setValue(0);
        frascosLote.setValue(0);
        machos.setValue(0);
        hermafroditas.setValue(0);
        hembrasAdultas.setValue(0);
        hembrasOvig.setValue(0);
        sinSexo.setValue(0);
        juveniles.setValue(0);

        formato.setItems(Ejemplares.formatoList());
        formato.setValue("Animal Completo");
        formato.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);

        frascosLote.setWidth("200px");
        machos.setWidthFull();
        hembrasOvig.setWidthFull();
        hermafroditas.setWidthFull();
        hembrasAdultas.setWidthFull();
        sinSexo.setWidthFull();

        HorizontalLayout numeroCatalogoLay = new HorizontalLayout(numeroCatalogo, numeroFrasco, comprobar);
        numeroCatalogoLay.setAlignItems(FlexComponent.Alignment.BASELINE);
        VerticalLayout primero = new VerticalLayout(formaAlmacenamiento, frascosLote);
        primero.setAlignItems(FlexComponent.Alignment.BASELINE);
        primero.setAlignItems(FlexComponent.Alignment.AUTO);
        HorizontalLayout formatoLay = new HorizontalLayout(formato);
        formatoLay.setAlignItems(FlexComponent.Alignment.BASELINE);

        VerticalLayout izqNum = new VerticalLayout(machos, hembrasAdultas, hembrasOvig, hermafroditas, sinSexo);
        VerticalLayout dchaNum = new VerticalLayout(huevos, larvas, juveniles);
        HorizontalLayout numerosLay = new HorizontalLayout(izqNum, dchaNum);

        VerticalLayout izq = new VerticalLayout(numeroCatalogoLay, primero, formatoLay);
        VerticalLayout dcha = new VerticalLayout(caracteristicasTipo, numerosLay);

        add(izq, dcha, save);
        save.addClickListener(event -> save());
    }

    public void setEjemplares(Ejemplares ejemplares) {
        binder.setBean(ejemplares);
    }


    public void save() {
        if (ejemplares != null && especieCreada != null) {
            ejemplares = binder.getBean();
            ejemplares.setEspecie(especieCreada);
            ejemplaresService.guardar(ejemplares);
            Notification.show("Valores guardados", 3000, Notification.Position.MIDDLE);
        } else
            Notification.show("No hay especie", 3000, Notification.Position.MIDDLE);
    }
}

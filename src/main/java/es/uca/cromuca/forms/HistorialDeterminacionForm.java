package es.uca.cromuca.forms;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.entities.HistorialDeterminacion;
import es.uca.cromuca.entities.Usuario;
import es.uca.cromuca.services.EspecieService;
import es.uca.cromuca.services.HistorialDeterminacionService;
import es.uca.cromuca.views.HistorialDeterminacionView;

public class HistorialDeterminacionForm extends FormLayout {
    public Button comprobar = new Button("Buscar");
    public TextField numeroCatalogo = new TextField("Num. catálogo");
    public TextField numeroFrasco = new TextField();
    private DatePicker fechaDeterminacion = new DatePicker("Fecha determinación");
    private TextField autor = new TextField("Autor determinación");
    private TextField taxon = new TextField("Taxón");
    private TextArea referenciasBibliograficas = new TextArea("Historial de conservación");
    private TextArea observaciones = new TextArea("Incidencias de conservación");
    private EspecieService especieService;
    private HistorialDeterminacionService historialDeterminacionService;
    private HistorialDeterminacionForm historialDeterminacionForm;
    private HistorialDeterminacionView historialDeterminacionView;
    public Especie especieCreada;
    private Binder<HistorialDeterminacion> binder = new Binder<>(HistorialDeterminacion.class);
    private Button save = new Button("Continuar");
    private HistorialDeterminacion historialDeterminacion = null;

    public HistorialDeterminacionForm(HistorialDeterminacionView historialDeterminacionView, EspecieService especieService, HistorialDeterminacionService historialDeterminacionService) {
        this.historialDeterminacionView = historialDeterminacionView;
        this.especieService = especieService;
        this.historialDeterminacionService = historialDeterminacionService;

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        comprobar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        binder.bindInstanceFields(this);

        comprobar.addClickListener(e -> {
            if (especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue()) != null) {
                especieCreada = especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue());
                historialDeterminacion = historialDeterminacionService.findByEspecie(especieCreada);
                if (historialDeterminacion != null) {
                    setHistorialDeterminacion(historialDeterminacion);
                    Notification.show("Datos cargados", 3000, Notification.Position.MIDDLE);
                } else {
                    Notification.show("No hay datos de historial de determinación", 3000, Notification.Position.MIDDLE);
                    historialDeterminacion = new HistorialDeterminacion();
                    setHistorialDeterminacion(historialDeterminacion);
                }
                historialDeterminacion.setEspecie(especieCreada);
            } else {
                Notification.show("Numero no existe", 3000, Notification.Position.MIDDLE);
                numeroCatalogo.setValue("");
                numeroFrasco.setValue("");
                historialDeterminacion = null;
            }
        });

        if (UI.getCurrent().getSession().getAttribute(Usuario.class) == null)
            save.setEnabled(false);

        observaciones.getStyle().set("minHeight", "150px");
        referenciasBibliograficas.getStyle().set("minHeight", "150px");

        HorizontalLayout primera = new HorizontalLayout(fechaDeterminacion, autor);
        primera.setAlignItems(FlexComponent.Alignment.BASELINE);

        referenciasBibliograficas.setSizeFull();
        observaciones.setWidthFull();

        VerticalLayout izq = new VerticalLayout(primera, referenciasBibliograficas, observaciones);

        add(izq, save);
        save.addClickListener(event -> save());
    }

    public void setHistorialDeterminacion(HistorialDeterminacion historialDeterminacion) {
        binder.setBean(historialDeterminacion);
    }

    public void save() {
        if (historialDeterminacion != null && especieCreada != null) {
            historialDeterminacion = binder.getBean();
            historialDeterminacion.setEspecie(especieCreada);
            historialDeterminacionService.guardar(historialDeterminacion);
            Notification.show("Valores guardados", 3000, Notification.Position.MIDDLE);
        } else
            Notification.show("No hay especie", 3000, Notification.Position.MIDDLE);
    }
}

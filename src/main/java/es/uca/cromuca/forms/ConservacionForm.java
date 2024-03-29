package es.uca.cromuca.forms;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import es.uca.cromuca.entities.Conservacion;
import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.entities.Usuario;
import es.uca.cromuca.services.ConservacionService;
import es.uca.cromuca.services.EspecieService;
import es.uca.cromuca.views.ConservacionView;

public class ConservacionForm extends FormLayout {
    public Button comprobar = new Button("Buscar");
    public TextField numeroCatalogo = new TextField("Num. catálogo");
    public TextField numeroFrasco = new TextField();
    private Checkbox estudiosGeneticos = new Checkbox("Ejemplares para estudios genéticos");
    private TextArea historial = new TextArea("Historial de conservación");
    private TextArea incidencias = new TextArea("Incidencias de conservación");
    private ComboBox<String> narcotizacion = new ComboBox<>("Narcotización");
    private ComboBox<String> fijador = new ComboBox<>("Fijador");
    private ComboBox<String> conservanteActual = new ComboBox<>("Conservante actual");
    private EspecieService especieService;
    private ConservacionService conservacionService;
    private ConservacionForm conservacionForm;
    private ConservacionView conservacionView;
    public Especie especieCreada;
    private Binder<Conservacion> binder = new Binder<>(Conservacion.class);
    private Button save = new Button("Continuar");
    private Conservacion conservacion = null;

    public ConservacionForm(ConservacionView conservacionView, EspecieService especieService, ConservacionService conservacionService) {
        this.conservacionView = conservacionView;
        this.especieService = especieService;
        this.conservacionService = conservacionService;

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        comprobar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        fijador.setItems(Conservacion.fijadorList());

        narcotizacion.setItems(Conservacion.narcotizacionList());

        conservanteActual.setItems(Conservacion.conservanteActualList());

        binder.bindInstanceFields(this);

        comprobar.addClickListener(e -> {
            if (especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue()) != null) {
                especieCreada = especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue());
                conservacion = conservacionService.findByEspecie(especieCreada);
                if (conservacion != null) {
                    setConservacion(conservacion);
                    Notification.show("Datos cargados", 3000, Notification.Position.MIDDLE);
                } else {
                    Notification.show("No hay datos de conservación", 3000, Notification.Position.MIDDLE);
                    conservacion = new Conservacion();
                    setConservacion(conservacion);
                }
                conservacion.setEspecie(especieCreada);
            } else {
                Notification.show("Numero no existe", 3000, Notification.Position.MIDDLE);
                numeroCatalogo.setValue("");
                numeroFrasco.setValue("");
                conservacion = null;
            }
        });

        if (UI.getCurrent().getSession().getAttribute(Usuario.class) == null) {
            save.setEnabled(false);
        }

        incidencias.getStyle().set("minHeight", "150px");
        historial.getStyle().set("minHeight", "150px");

        historial.setSizeFull();
        incidencias.setWidthFull();

        VerticalLayout izq = new VerticalLayout(narcotizacion, fijador, estudiosGeneticos, conservanteActual, incidencias);
        VerticalLayout dcha = new VerticalLayout(historial);

        add(izq, dcha, save);
        save.addClickListener(event -> save());
    }

    public void setConservacion(Conservacion conservacion) {
        binder.setBean(conservacion);
    }


    public void save() {
        if (conservacion != null && especieCreada != null) {
            conservacion = binder.getBean();
            conservacion.setEspecie(especieCreada);
            conservacionService.guardar(conservacion);
            Notification.show("Valores guardados", 3000, Notification.Position.MIDDLE);
        } else
            Notification.show("No hay especie", 3000, Notification.Position.MIDDLE);
    }
}

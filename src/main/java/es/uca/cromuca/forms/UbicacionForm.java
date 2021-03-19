package es.uca.cromuca.forms;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.entities.Ubicacion;
import es.uca.cromuca.entities.Usuario;
import es.uca.cromuca.services.EspecieService;
import es.uca.cromuca.services.UbicacionService;
import es.uca.cromuca.views.UbicacionView;

public class UbicacionForm extends FormLayout {
    public Button comprobar = new Button("Buscar");
    public TextField numeroCatalogo = new TextField("Num. catálogo");
    public TextField numeroFrasco = new TextField();
    private IntegerField armario = new IntegerField("Armario");
    private IntegerField estante = new IntegerField("Estante");
    private IntegerField cajon = new IntegerField("Cajón");
    private RadioButtonGroup<String> estatus = new RadioButtonGroup<>();
    private EspecieService especieService;
    private UbicacionService ubicacionService;
    private UbicacionView ubicacionView;
    public Especie especieCreada;
    private Binder<Ubicacion> binder = new Binder<>(Ubicacion.class);
    private Button save = new Button("Continuar");
    private Ubicacion ubicacion = null;

    public UbicacionForm(UbicacionView ubicacionView, EspecieService especieService, UbicacionService ubicacionService) {
        this.ubicacionView = ubicacionView;
        this.especieService = especieService;
        this.ubicacionService = ubicacionService;

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        comprobar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        binder.bindInstanceFields(this);

        comprobar.addClickListener(e -> {
            if (especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue()) != null) {
                especieCreada = especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue());
                ubicacion = ubicacionService.findByEspecie(especieCreada);
                if (ubicacion != null) {
                    setUbicacion(ubicacion);
                    Notification.show("Datos cargados", 3000, Notification.Position.MIDDLE);
                } else {
                    Notification.show("No hay datos de ubicaciones", 3000, Notification.Position.MIDDLE);
                    ubicacion = new Ubicacion();
                    ubicacion.setEspecie(especieCreada);
                    ubicacionService.guardar(ubicacion);
                    setUbicacion(ubicacion);
                }
                UI.getCurrent().getSession().setAttribute(Ubicacion.class, ubicacion);
                ubicacion.setEspecie(especieCreada);
                ubicacionView.updateList();
            } else {
                Notification.show("Numero no existe", 3000, Notification.Position.MIDDLE);
                numeroCatalogo.setValue("");
                numeroFrasco.setValue("");
                ubicacion = null;
            }
        });

        if (UI.getCurrent().getSession().getAttribute(Usuario.class) == null)
            save.setEnabled(false);

        estatus.setItems(Ubicacion.estatusList());
        estatus.setValue("Disponible");

        HorizontalLayout ubicacion = new HorizontalLayout(armario, estante, cajon, estatus, save);
        ubicacion.setAlignItems(FlexComponent.Alignment.BASELINE);

        //VerticalLayout izq = new VerticalLayout(numeroCatalogoLay, ubicacion);

        add(ubicacion);

        setSizeFull();
        save.addClickListener(event -> save());
    }

    public void setUbicacion(Ubicacion ubicacion) {
        binder.setBean(ubicacion);
    }

    public void save() {
        if (ubicacion != null && especieCreada != null) {
            ubicacion = binder.getBean();
            ubicacion.setEspecie(especieCreada);
            ubicacionService.guardar(ubicacion);
            Notification.show("Valores guardados", 3000, Notification.Position.MIDDLE);
        } else
            Notification.show("No hay especie", 3000, Notification.Position.MIDDLE);
    }
}

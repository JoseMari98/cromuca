package es.uca.cromuca.forms;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import es.uca.cromuca.entities.Prestamo;
import es.uca.cromuca.entities.Ubicacion;
import es.uca.cromuca.services.PrestamoService;
import es.uca.cromuca.services.UbicacionService;
import es.uca.cromuca.views.UbicacionView;

public class PrestamoForm extends FormLayout {
    private TextField nombre = new TextField("Nombre");
    private TextField institucion = new TextField("Institución");
    private TextField telefono = new TextField("Teléfono");
    private EmailField email = new EmailField("E-mail");
    private IntegerField numEjemplares = new IntegerField("Ejemplares");
    private DatePicker fecha = new DatePicker("Fecha");
    private DatePicker fechaLimite = new DatePicker("Fecha limite");
    private Checkbox devuelto = new Checkbox("Devuelto");
    private TextArea observaciones = new TextArea("Observaciones");
    private Button save = new Button("Guardar");
    private Button delete = new Button("Borrar");
    private UbicacionView ubicacionView;
    private Binder<Prestamo> binder = new Binder<>(Prestamo.class);
    private UbicacionService ubicacionService;
    private PrestamoService prestamoService;
    private Ubicacion ubicacion;

    public PrestamoForm(UbicacionView ubicacionView, UbicacionService ubicacionService, PrestamoService prestamoService) {
        this.ubicacionService = ubicacionService;
        this.prestamoService = prestamoService;
        this.ubicacionView = ubicacionView;

        ubicacion = UI.getCurrent().getSession().getAttribute(Ubicacion.class);

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        VerticalLayout izq = new VerticalLayout(fecha, nombre, email, fechaLimite, observaciones);
        VerticalLayout dcha = new VerticalLayout(numEjemplares, institucion, telefono, devuelto);
        HorizontalLayout formulario = new HorizontalLayout(izq, dcha);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(formulario, buttons);
        save.addClickShortcut(Key.ENTER);

        numEjemplares.setValue(0);
        binder.bindInstanceFields(this);

        save.addClickListener(event -> {
            if (binder.getBean() != null)
                save();
        });
        delete.addClickListener(event -> {
            if (binder.getBean() != null)
                delete();
        });
    }

    public void setPrestamo(Prestamo prestamo) {
        binder.setBean(prestamo);

        if (prestamo == null) {
            setVisible(false);
        } else {
            setVisible(true);
            //this.modelo.focus();
        }
    }

    public void save() {
        Prestamo prestamo = binder.getBean();
        ubicacion = UI.getCurrent().getSession().getAttribute(Ubicacion.class);
        prestamo.setUbicacion(ubicacion);
        prestamoService.guardar(prestamo);
        this.ubicacionView.updateList();
        setPrestamo(null);
    }

    public void delete() {
        Prestamo prestamo = binder.getBean();
        ubicacion = UI.getCurrent().getSession().getAttribute(Ubicacion.class);
        prestamo.setUbicacion(ubicacion);
        prestamoService.borrar(prestamo);
        this.ubicacionView.updateList();
        setPrestamo(null);
    }
}

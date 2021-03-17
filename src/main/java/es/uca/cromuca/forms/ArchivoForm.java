package es.uca.cromuca.forms;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import es.uca.cromuca.entities.Archivo;
import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.entities.enums.FormatoMultimedia;
import es.uca.cromuca.services.ArchivoService;
import es.uca.cromuca.services.EspecieService;
import es.uca.cromuca.views.ArchivoView;

public class ArchivoForm extends FormLayout {

    private TextField autor = new TextField("Autor");
    private TextField link = new TextField("link");
    private TextArea observaciones = new TextArea("Observaciones");
    private DatePicker fecha = new DatePicker("Fecha");
    private ComboBox<FormatoMultimedia> formatoMultimedia = new ComboBox<>("Formato");
    private Button save = new Button("Guardar");
    private Button delete = new Button("Borrar");
    private Button volver = new Button("Volver");
    private ArchivoView archivoView;
    private Binder<Archivo> binder = new Binder<>(Archivo.class);
    private EspecieService especieService;
    private ArchivoService archivoService;
    public Archivo archivo = null;
    public Especie especieCreada;

    public ArchivoForm(ArchivoView archivoView, EspecieService especieService, ArchivoService archivoService) {
        this.especieService = especieService;
        this.archivoService = archivoService;
        this.archivoView = archivoView;

        HorizontalLayout buttons = new HorizontalLayout(save, delete, volver);
        VerticalLayout izq = new VerticalLayout(formatoMultimedia, fecha);
        VerticalLayout dcha = new VerticalLayout(autor, link);
        HorizontalLayout forms = new HorizontalLayout(izq, dcha);
        volver.addThemeVariants(ButtonVariant.LUMO_ERROR);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        formatoMultimedia.setItems(FormatoMultimedia.values());
        save.addClickShortcut(Key.ENTER);

        add(forms, observaciones, buttons);

        binder.bindInstanceFields(this);

        observaciones.getStyle().set("minHeight", "150px");

        volver.addClickListener(e -> {
            UI.getCurrent().navigate("ClasificacionView");
        });

        save.addClickListener(event -> {
            if (binder.getBean() != null)
                save();
        });
        delete.addClickListener(event -> {
            if (binder.getBean() != null)
                delete();
        });
    }

    public void setArchivo(Archivo archivo) {
        binder.setBean(archivo);

        if (archivo == null) {
            setVisible(false);
        } else {
            setVisible(true);
            //this.modelo.focus();
        }
    }

    public void save() {
        Archivo archivo = binder.getBean();
        archivoService.guardar(archivo);
        this.archivoView.updateList();
        setArchivo(null);
    }

    public void delete() {
        Archivo archivo = binder.getBean();
        archivoService.borrar(archivo);
        this.archivoView.updateList();
        setArchivo(null);
    }
}

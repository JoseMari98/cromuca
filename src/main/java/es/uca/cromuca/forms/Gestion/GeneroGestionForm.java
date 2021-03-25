package es.uca.cromuca.forms.Gestion;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.entities.Familia;
import es.uca.cromuca.entities.Genero;
import es.uca.cromuca.services.EspecieService;
import es.uca.cromuca.services.FamiliaService;
import es.uca.cromuca.services.GeneroService;
import es.uca.cromuca.views.Gestion.GeneroGestionView;

public class GeneroGestionForm extends FormLayout {
    private TextField genero = new TextField("Género");
    private ComboBox<Familia> familia = new ComboBox<>("Familia");
    private Button save = new Button("Guardar");
    private Button delete = new Button("Borrar");
    private GeneroGestionView generoGestionView;
    private Binder<Genero> binder = new Binder<>(Genero.class);
    private EspecieService especieService;
    private GeneroService generoService;
    private FamiliaService familiaService;

    public GeneroGestionForm(GeneroGestionView generoGestionView, GeneroService generoService, EspecieService especieService, FamiliaService familiaService) {
        this.generoService = generoService;
        this.especieService = especieService;
        this.familiaService = familiaService;
        this.generoGestionView = generoGestionView;

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(familia, genero, buttons);
        familia.setRequired(true);
        familia.setItems(familiaService.findAll());
        familia.setItemLabelGenerator(Familia::getFamilia);
        genero.setRequired(true);
        save.addClickShortcut(Key.ENTER);

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

    public void setGenero(Genero genero) {
        binder.setBean(genero);

        if (genero == null) {
            setVisible(false);
        } else {
            setVisible(true);
            //this.modelo.focus();
        }
    }

    public void save() {
        Genero genero = binder.getBean();
        if (binder.validate().isOk() && !binder.getBean().getGenero().equals("")) {
            generoService.guardar(genero);
            this.generoGestionView.updateList();
            setGenero(null);
        } else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }

    public void delete() {
        Genero genero = binder.getBean();
        if (binder.validate().isOk() && !binder.getBean().getGenero().equals("")) {
            for (Especie e : especieService.findByGenero(genero)) {
                especieService.borrar(e);
            }
            generoService.borrar(genero);
            this.generoGestionView.updateList();
            setGenero(null);
        } else
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);

    }
}

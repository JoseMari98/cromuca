package es.uca.cromuca.forms.Gestion;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import es.uca.cromuca.entities.CategoriaTaxonomicaPpal;
import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.entities.Familia;
import es.uca.cromuca.entities.Genero;
import es.uca.cromuca.services.*;
import es.uca.cromuca.views.Gestion.FamiliaGestionView;

public class FamiliaGestionForm extends FormLayout {
    private TextField familia = new TextField("Familia");
    private ComboBox<CategoriaTaxonomicaPpal> categoriaTaxonomicaPpal = new ComboBox<>("Categoria Taxonomica");
    private Button save = new Button("Guardar");
    private Button delete = new Button("Borrar");
    private Button volver = new Button("Volver");
    private FamiliaGestionView familiaGestionView;
    private Binder<Familia> binder = new Binder<>(Familia.class);
    private EspecieService especieService;
    private GeneroService generoService;
    private FamiliaService familiaService;
    private PhylumService phylumService;
    private CategoriaTaxonomicaService categoriaTaxonomicaService;

    public FamiliaGestionForm(FamiliaGestionView familiaGestionView, GeneroService generoService, EspecieService especieService, FamiliaService familiaService,
                              CategoriaTaxonomicaService categoriaTaxonomicaService, PhylumService phylumService) {
        this.generoService = generoService;
        this.especieService = especieService;
        this.familiaService = familiaService;
        this.phylumService = phylumService;
        this.categoriaTaxonomicaService = categoriaTaxonomicaService;
        this.categoriaTaxonomicaService = categoriaTaxonomicaService;
        this.familiaGestionView = familiaGestionView;

        HorizontalLayout buttons = new HorizontalLayout(save, delete, volver);
        volver.addThemeVariants(ButtonVariant.LUMO_ERROR);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(categoriaTaxonomicaPpal, familia, buttons);
        categoriaTaxonomicaPpal.setRequired(true);
        categoriaTaxonomicaPpal.setItems(categoriaTaxonomicaService.findAll());
        categoriaTaxonomicaPpal.setItemLabelGenerator(CategoriaTaxonomicaPpal::getCategoriaTaxonomicaPpal);
        familia.setRequired(true);
        save.addClickShortcut(Key.ENTER);

        binder.bindInstanceFields(this);

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

    public void setFamilia(Familia familia) {
        binder.setBean(familia);

        if (familia == null) {
            setVisible(false);
        } else {
            setVisible(true);
            //this.modelo.focus();
        }
    }

    public void save() {
        Familia familia = binder.getBean();
        if (binder.validate().isOk() && !binder.getBean().getFamilia().equals("")) {
            familiaService.guardar(familia);
            this.familiaGestionView.updateList();
            setFamilia(null);
        } else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }

    public void delete() {
        Familia familia = binder.getBean();
        if (binder.validate().isOk() && !binder.getBean().getFamilia().equals("")) {
            for (Genero genero : generoService.findByFamilia(familia)) {
                for (Especie especie : especieService.findByGenero(genero)) {
                    especieService.borrar(especie);
                }
                generoService.borrar(genero);
            }
            familiaService.borrar(familia);
            this.familiaGestionView.updateList();
            setFamilia(null);
        } else
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
    }
}

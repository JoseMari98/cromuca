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
import es.uca.cromuca.entities.*;
import es.uca.cromuca.services.*;
import es.uca.cromuca.views.Gestion.CategoriaTaxonomicaGestionView;

public class CategoriaTaxonomicaGestionForm extends FormLayout {
    private TextField categoriaTaxonomicaPpal = new TextField("Categoria Taxonomica");
    private ComboBox<Phylum> phylum = new ComboBox<>("Phylum");
    private Button save = new Button("Añadir");
    private Button delete = new Button("Borrar");
    private CategoriaTaxonomicaGestionView categoriaTaxonomicaGestionView;
    private Binder<CategoriaTaxonomicaPpal> binder = new Binder<>(CategoriaTaxonomicaPpal.class);
    private EspecieService especieService;
    private GeneroService generoService;
    private FamiliaService familiaService;
    private PhylumService phylumService;
    private CategoriaTaxonomicaService categoriaTaxonomicaService;

    public CategoriaTaxonomicaGestionForm(CategoriaTaxonomicaGestionView categoriaTaxonomicaGestionView, GeneroService generoService, EspecieService especieService, FamiliaService familiaService,
                                          CategoriaTaxonomicaService categoriaTaxonomicaService, PhylumService phylumService) {
        this.generoService = generoService;
        this.especieService = especieService;
        this.familiaService = familiaService;
        this.phylumService = phylumService;
        this.categoriaTaxonomicaService = categoriaTaxonomicaService;
        this.categoriaTaxonomicaGestionView = categoriaTaxonomicaGestionView;

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(phylum, categoriaTaxonomicaPpal, buttons);
        categoriaTaxonomicaPpal.setRequired(true);
        phylum.setRequired(true);
        phylum.setItems(phylumService.findAll());
        phylum.setItemLabelGenerator(Phylum::getPhylum);
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

    public void setCategoriaTaxonomicaPpal(CategoriaTaxonomicaPpal categoriaTaxonomicaPpal) {
        binder.setBean(categoriaTaxonomicaPpal);

        if (categoriaTaxonomicaPpal == null) {
            setVisible(false);
        } else {
            setVisible(true);
            //this.modelo.focus();
        }
    }

    public void save() {
        CategoriaTaxonomicaPpal categoriaTaxonomicaPpal = binder.getBean();
        if (binder.validate().isOk() && !binder.getBean().getCategoriaTaxonomicaPpal().equals("")) {
            categoriaTaxonomicaService.guardar(categoriaTaxonomicaPpal);
            this.categoriaTaxonomicaGestionView.updateList();
            setCategoriaTaxonomicaPpal(null);
        } else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }

    public void delete() {
        CategoriaTaxonomicaPpal categoriaTaxonomicaPpal = binder.getBean();
        if (binder.validate().isOk() && !binder.getBean().getCategoriaTaxonomicaPpal().equals("")) {

            for (Familia familia : familiaService.findByCategoria(categoriaTaxonomicaPpal)) {
                for (Genero genero : generoService.findByFamilia(familia)) {
                    for (Especie especie : especieService.findByGenero(genero)) {
                        especieService.borrar(especie);
                    }
                    generoService.borrar(genero);
                }
                familiaService.borrar(familia);
            }
            categoriaTaxonomicaService.borrar(categoriaTaxonomicaPpal);

            this.categoriaTaxonomicaGestionView.updateList();
            setCategoriaTaxonomicaPpal(null);
        } else
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);

    }
}

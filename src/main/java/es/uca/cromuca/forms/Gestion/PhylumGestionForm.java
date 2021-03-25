package es.uca.cromuca.forms.Gestion;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import es.uca.cromuca.entities.*;
import es.uca.cromuca.services.*;
import es.uca.cromuca.views.Gestion.PhylumGestionView;

public class PhylumGestionForm extends FormLayout {
    private TextField phylum = new TextField("Phylum");
    private Button save = new Button("Guardar");
    private Button delete = new Button("Borrar");
    private PhylumGestionView phylumGestionView;
    private Binder<Phylum> binder = new Binder<>(Phylum.class);
    private EspecieService especieService;
    private GeneroService generoService;
    private FamiliaService familiaService;
    private PhylumService phylumService;
    private CategoriaTaxonomicaService categoriaTaxonomicaService;

    public PhylumGestionForm(PhylumGestionView phylumGestionView, GeneroService generoService, EspecieService especieService, FamiliaService familiaService,
                             CategoriaTaxonomicaService categoriaTaxonomicaService, PhylumService phylumService) {
        this.generoService = generoService;
        this.especieService = especieService;
        this.familiaService = familiaService;
        this.phylumService = phylumService;
        this.categoriaTaxonomicaService = categoriaTaxonomicaService;
        this.phylumGestionView = phylumGestionView;

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(phylum, buttons);
        phylum.setRequired(true);
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

    public void setPhylum(Phylum phylum) {
        binder.setBean(phylum);

        if (phylum == null) {
            setVisible(false);
        } else {
            setVisible(true);
            //this.modelo.focus();
        }
    }

    public void save() {
        Phylum phylum = binder.getBean();
        if (binder.validate().isOk() && !binder.getBean().getPhylum().equals("")) {
            phylumService.guardar(phylum);
            this.phylumGestionView.updateList();
            setPhylum(null);
        } else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }

    public void delete() {
        Phylum phylum = binder.getBean();
        if (binder.validate().isOk() && !binder.getBean().getPhylum().equals("")) {
            for (CategoriaTaxonomicaPpal categoriaTaxonomicaPpal : categoriaTaxonomicaService.findByPhylum(phylum)) {
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
            }
            phylumService.borrar(phylum);
            this.phylumGestionView.updateList();
            setPhylum(null);
        } else
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);

    }
}

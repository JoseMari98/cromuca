/*package es.uca.cromuca.forms;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import es.uca.cromuca.entities.*;
import es.uca.cromuca.services.*;
import es.uca.cromuca.views.ClasificacionView;
import es.uca.cromuca.views.DatosMuestreoView;

import java.time.LocalDate;

public class DatosMuestreoForm extends FormLayout {
    private Button comprobar = new Button("Buscar");
    private Button addThingsPhylum = new Button("+");
    private Button addThingsCategoria = new Button("+");
    private Button addThingsFamilia = new Button("+");
    private Button addThingsGenero = new Button("+");
    private Label numeroCatalogoLabel = new Label("Núm. Catálogo (forma XXXX-XX)");
    private Label phylumLabel = new Label("Phylum");
    private Label categoriaLabel = new Label("Categoria tax. ppal.");
    private Label familiaLabel = new Label("Familia");
    private Label generoLabel = new Label("Género");
    private TextField numeroCatalogo = new TextField();
    private TextField autorAno = new TextField("Autor y año");
    private TextField especie = new TextField("Especie");
    private DatePicker fechaAlta = new DatePicker("Fecha alta");
    private ComboBox<Genero> genero = new ComboBox<>();
    private ComboBox<Familia> familia = new ComboBox<>();
    private ComboBox<CategoriaTaxonomicaPpal> categoriaTaxonomicaPpal = new ComboBox<>();
    private ComboBox<Phylum> phylum = new ComboBox<>();
    private Checkbox nuevo = new Checkbox("Nuevo registro");
    private EspecieService especieService;
    private DatosMuestreoService datosMuestreoService;
    private DatosMuestreoView datosMuestreoView;
    private Especie especieCreada;
    private Button save = new Button("Continuar");

    public DatosMuestreoForm(DatosMuestreoView datosMuestreoView, EspecieService especieService, DatosMuestreoService datosMuestreoService) {
        this.datosMuestreoService = datosMuestreoService;
        this.especieService = especieService;
        this.datosMuestreoView = datosMuestreoView;

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        comprobar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        phylum.setRequired(true);
        phylum.setItems(phylumService.findAll());
        phylum.setItemLabelGenerator(Phylum::getPhylum);
        phylum.addValueChangeListener(e -> {
            if(phylum.getValue() != null){
                categoriaTaxonomicaPpal.setRequired(true);
                categoriaTaxonomicaPpal.setItems(categoriaTaxonomicaService.findByPhylum(phylum.getValue()));
                categoriaTaxonomicaPpal.setItemLabelGenerator(CategoriaTaxonomicaPpal::getCategoriaTaxonomicaPpal);
            }
        });
        categoriaTaxonomicaPpal.addValueChangeListener(e -> {
            if(categoriaTaxonomicaPpal.getValue() != null){
                familia.setRequired(true);
                familia.setItems(familiaService.findByCategoria(categoriaTaxonomicaPpal.getValue()));
                familia.setItemLabelGenerator(Familia::getFamilia);
            }
        });
        familia.addValueChangeListener(e -> {
            if(familia.getValue() != null){
                genero.setRequired(true);
                genero.setItems(generoService.findByFamilia(familia.getValue()));
                genero.setItemLabelGenerator(Genero::getGenero);
            }
        });

        nuevo.addClickListener(e -> {
            if(nuevo.getValue()){
                if(especieService.findLastid() == null){ //no hay ninguno insertado
                    numeroCatalogo.setValue("0000-01");
                } else{ //hay insertado
                    Long nuevoId = especieService.findLastid().getId() + 1;
                    switch(especieService.findLastid().getId().toString().length()){
                        case 1: numeroCatalogo.setValue("000" + nuevoId.toString() + "-01"); break;
                        case 2: numeroCatalogo.setValue("00" + nuevoId.toString() + "-01"); break;
                        case 3: numeroCatalogo.setValue("0" + nuevoId.toString() + "-01"); break;
                        case 4: numeroCatalogo.setValue(nuevoId.toString() + "-01"); break;
                    }
                }
                numeroCatalogo.setEnabled(false);
                fechaAlta.setValue(LocalDate.now());
            } else{
                numeroCatalogo.setValue("");
                numeroCatalogo.setEnabled(true);
            }
        });
        addThingsPhylum.addClickListener(e -> {
            UI.getCurrent().navigate("GestionPhylum");
        });
        addThingsCategoria.addClickListener(e -> {
            UI.getCurrent().navigate("GestionCategoria");
        });
        addThingsFamilia.addClickListener(e -> {
            UI.getCurrent().navigate("GestionFamilia");
        });
        addThingsGenero.addClickListener(e -> {
            UI.getCurrent().navigate("GestionGenero");
        });
        comprobar.addClickListener(e -> {
            if(especieService.findByNumCatalogo(numeroCatalogo.getValue()) != null){
                especieCreada = especieService.findByNumCatalogo(numeroCatalogo.getValue());
                especie.setValue(especieCreada.getEspecie());
                autorAno.setValue(especieCreada.getAutorAno());
                fechaAlta.setValue(especieCreada.getFechaAlta());
                numeroCatalogo.setValue(especieCreada.getNumeroCatalogo());

                Genero generoAux = especieCreada.getGenero();

                Familia familiaAux = generoService.buscarId(generoAux.getId()).get().getFamilia();

                CategoriaTaxonomicaPpal categoriaAux = familiaService.buscarId(familiaAux.getId()).get().getCategoriaTaxonomicaPpal();

                phylum.setValue(categoriaTaxonomicaService.buscarId(categoriaAux.getId()).get().getPhylum());

                categoriaTaxonomicaPpal.setValue(categoriaAux);
                familia.setValue(familiaAux);
                genero.setValue(generoAux);
            }
            else{
                Notification.show("Numero no existe", 3000, Notification.Position.MIDDLE);
                numeroCatalogo.setValue("");
            }
        });
        HorizontalLayout numeroCatalogoLay = new HorizontalLayout(numeroCatalogoLabel, numeroCatalogo, comprobar, nuevo);
        HorizontalLayout phylumLay = new HorizontalLayout(phylumLabel, phylum, addThingsPhylum);
        HorizontalLayout categoriaLay = new HorizontalLayout(categoriaLabel, categoriaTaxonomicaPpal, addThingsCategoria);
        HorizontalLayout familiaLay = new HorizontalLayout(familiaLabel, familia, addThingsFamilia);
        HorizontalLayout generoLay = new HorizontalLayout(generoLabel, genero, addThingsGenero);
        HorizontalLayout especieLay = new HorizontalLayout(especie, autorAno, fechaAlta);
        VerticalLayout izq = new VerticalLayout(numeroCatalogoLay, categoriaLay, generoLay);
        VerticalLayout dcha = new VerticalLayout(phylumLay, familiaLay, especieLay, save);

        add(izq, dcha);
        //save.addClickShortcut(Key.ENTER);
        save.addClickListener(event -> save());
    }

    public void save(){
        if(nuevo.getValue()){
            if(!numeroCatalogo.getValue().isEmpty() && phylum.getValue() != null && categoriaTaxonomicaPpal.getValue() != null &&
                    familia.getValue() != null && genero.getValue() != null && !especie.getValue().isEmpty() &&
                    !autorAno.getValue().isEmpty() && fechaAlta.getValue() != null){
                Especie e = new Especie();
                e.setEspecie(especie.getValue());
                e.setAutorAno(autorAno.getValue());
                e.setFechaAlta(fechaAlta.getValue());
                e.setNumeroCatalogo(numeroCatalogo.getValue());
                e.setGenero(genero.getValue());
                especieService.guardar(e);
            } else
                Notification.show("Comprueba tus datos", 3000, Notification.Position.MIDDLE);
        } else{
            if(!numeroCatalogo.getValue().isEmpty() && phylum.getValue() != null && categoriaTaxonomicaPpal.getValue() != null &&
                    familia.getValue() != null && genero.getValue() != null && !especie.getValue().isEmpty() &&
                    !autorAno.getValue().isEmpty() && fechaAlta.getValue() != null){
                especieCreada.setEspecie(especie.getValue());
                especieCreada.setAutorAno(autorAno.getValue());
                especieCreada.setFechaAlta(fechaAlta.getValue());
                especieCreada.setNumeroCatalogo(numeroCatalogo.getValue());
                especieCreada.setGenero(genero.getValue());
                especieService.guardar(especieCreada);
            } else
                Notification.show("Comprueba tus datos", 3000, Notification.Position.MIDDLE);
        }

        Notification.show("Guardado con éxito", 3000, Notification.Position.MIDDLE);
    }
}
*/
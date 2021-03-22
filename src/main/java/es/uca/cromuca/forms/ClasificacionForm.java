package es.uca.cromuca.forms;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import es.uca.cromuca.entities.*;
import es.uca.cromuca.services.*;
import es.uca.cromuca.views.ArchivoView;

import java.time.LocalDate;

public class ClasificacionForm extends FormLayout {
    private Button comprobar = new Button("Buscar");
    private Button duplicar = new Button("Duplicar");
    private Button addThingsPhylum = new Button("+");
    private Button addThingsCategoria = new Button("+");
    private Button addThingsFamilia = new Button("+");
    private Button addThingsGenero = new Button("+");
    private TextField numeroCatalogo = new TextField("Núm. Catálogo");
    private TextField numeroFrasco = new TextField();
    private TextField autorAno = new TextField("Autor y año");
    private TextField especie = new TextField("Especie");
    private DatePicker fechaAlta = new DatePicker("Fecha alta");
    private ComboBox<Genero> genero = new ComboBox<>("Género");
    private ComboBox<Familia> familia = new ComboBox<>("Familia");
    private ComboBox<CategoriaTaxonomicaPpal> categoriaTaxonomicaPpal = new ComboBox<>("Categoría tax. ppal.");
    private ComboBox<Phylum> phylum = new ComboBox<>("Phylum");
    private Checkbox nuevo = new Checkbox("Nuevo registro");
    public DatosMuestreoForm datosMuestreoForm;
    public EjemplaresForm ejemplaresForm;
    public ConservacionForm conservacionForm;
    public UbicacionForm ubicacionForm;
    public ArchivoForm archivoForm;
    public HistorialDeterminacionForm historialDeterminacionForm;
    public ArchivoView archivoView;
    private EspecieService especieService;
    private GeneroService generoService;
    private FamiliaService familiaService;
    private PhylumService phylumService;
    private CategoriaTaxonomicaService categoriaTaxonomicaService;
    private Especie especieCreada;
    private Button save = new Button("Continuar");

    public ClasificacionForm(GeneroService generoService, EspecieService especieService, FamiliaService familiaService,
                             CategoriaTaxonomicaService categoriaTaxonomicaService, PhylumService phylumService) {
        this.datosMuestreoForm = datosMuestreoForm;
        this.generoService = generoService;
        this.especieService = especieService;
        this.familiaService = familiaService;
        this.phylumService = phylumService;
        this.categoriaTaxonomicaService = categoriaTaxonomicaService;

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        comprobar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        phylum.setItems(phylumService.findAll());
        phylum.setItemLabelGenerator(Phylum::getPhylum);
        phylum.addValueChangeListener(e -> {
            if(phylum.getValue() != null){
                categoriaTaxonomicaPpal.setItems(categoriaTaxonomicaService.findByPhylum(phylum.getValue()));
                categoriaTaxonomicaPpal.setItemLabelGenerator(CategoriaTaxonomicaPpal::getCategoriaTaxonomicaPpal);
            }
        });
        categoriaTaxonomicaPpal.addValueChangeListener(e -> {
            if(categoriaTaxonomicaPpal.getValue() != null){
                familia.setItems(familiaService.findByCategoria(categoriaTaxonomicaPpal.getValue()));
                familia.setItemLabelGenerator(Familia::getFamilia);
            }
        });
        familia.addValueChangeListener(e -> {
            if (familia.getValue() != null) {
                genero.setItems(generoService.findByFamilia(familia.getValue()));
                genero.setItemLabelGenerator(Genero::getGenero);
            }
        });

        if (UI.getCurrent().getSession().getAttribute(Usuario.class) == null) {
            duplicar.setEnabled(false);
            save.setEnabled(false);
            addThingsCategoria.setEnabled(false);
            addThingsFamilia.setEnabled(false);
            addThingsGenero.setEnabled(false);
            addThingsPhylum.setEnabled(false);
        }

        nuevo.addClickListener(e -> {
            if (nuevo.getValue()) {
                if (especieService.findLastid() == null) { //no hay ninguno insertado
                    numeroCatalogo.setValue("0001");
                } else { //hay insertado
                    String catalogoString = especieService.nuevoNumero();
                    Integer numero = Integer.parseInt(catalogoString) + 1;
                    switch (numero.toString().length()) {
                        case 1:
                            numeroCatalogo.setValue("000" + numero.toString());
                            break;
                        case 2:
                            numeroCatalogo.setValue("00" + numero.toString());
                            break;
                        case 3:
                            numeroCatalogo.setValue("0" + numero.toString());
                            break;
                        case 4:
                            numeroCatalogo.setValue(numero.toString());
                            break;
                    }
                }
                numeroFrasco.setValue("01");
                numeroCatalogo.setEnabled(false);
                numeroFrasco.setEnabled(false);
                fechaAlta.setValue(LocalDate.now());
            } else{
                numeroCatalogo.setValue("");
                numeroFrasco.setValue("");
                numeroFrasco.setEnabled(true);
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
            if (especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue()) != null) {
                if (UI.getCurrent().getInternals().getActiveViewLocation().getFirstSegment().equals("DatosMuestreoView")) {
                    datosMuestreoForm.numeroFrasco.setValue(numeroFrasco.getValue());
                    datosMuestreoForm.numeroCatalogo.setValue(numeroCatalogo.getValue());
                    datosMuestreoForm.especieCreada = especieCreada;
                    datosMuestreoForm.comprobar.click();
                }
                if (UI.getCurrent().getInternals().getActiveViewLocation().getFirstSegment().equals("EjemplaresView")) {
                    ejemplaresForm.numeroFrasco.setValue(numeroFrasco.getValue());
                    ejemplaresForm.numeroCatalogo.setValue(numeroCatalogo.getValue());
                    ejemplaresForm.especieCreada = especieCreada;
                    ejemplaresForm.comprobar.click();
                }
                if (UI.getCurrent().getInternals().getActiveViewLocation().getFirstSegment().equals("ConservacionView")) {
                    conservacionForm.numeroFrasco.setValue(numeroFrasco.getValue());
                    conservacionForm.numeroCatalogo.setValue(numeroCatalogo.getValue());
                    conservacionForm.especieCreada = especieCreada;
                    conservacionForm.comprobar.click();
                }
                if (UI.getCurrent().getInternals().getActiveViewLocation().getFirstSegment().equals("HistorialDeterminacionView")) {
                    historialDeterminacionForm.numeroFrasco.setValue(numeroFrasco.getValue());
                    historialDeterminacionForm.numeroCatalogo.setValue(numeroCatalogo.getValue());
                    historialDeterminacionForm.especieCreada = especieCreada;
                    historialDeterminacionForm.comprobar.click();
                }
                if (UI.getCurrent().getInternals().getActiveViewLocation().getFirstSegment().equals("UbicacionView")) {
                    ubicacionForm.numeroFrasco.setValue(numeroFrasco.getValue());
                    ubicacionForm.numeroCatalogo.setValue(numeroCatalogo.getValue());
                    ubicacionForm.especieCreada = especieCreada;
                    ubicacionForm.comprobar.click();
                }
                if (UI.getCurrent().getInternals().getActiveViewLocation().getFirstSegment().equals("ArchivoView")) {
                    archivoView.numeroFrasco.setValue(numeroFrasco.getValue());
                    archivoView.numeroCatalogo.setValue(numeroCatalogo.getValue());
                    archivoForm.especieCreada = especieCreada;
                    archivoView.comprobar.click();
                }
                especieCreada = especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue());
                especie.setValue(especieCreada.getEspecie());
                autorAno.setValue(especieCreada.getAutorAno());
                fechaAlta.setValue(especieCreada.getFechaAlta());
                numeroCatalogo.setValue(especieCreada.getNumeroCatalogo());
                numeroFrasco.setValue(especieCreada.getNumeroFrasco());
                phylum.setValue(especieCreada.getPhylum());
                categoriaTaxonomicaPpal.setValue(especieCreada.getCategoriaTaxonomicaPpal());
                familia.setValue(especieCreada.getFamilia());
                genero.setValue(especieCreada.getGenero());
            } else {
                Notification.show("Numero no existe", 3000, Notification.Position.MIDDLE);
                numeroCatalogo.setValue("");
                numeroFrasco.setValue("");
            }
        });

        duplicar.addClickListener(e -> {
            if (!numeroCatalogo.getValue().isEmpty() && phylum.getValue() != null && categoriaTaxonomicaPpal.getValue() != null &&
                    familia.getValue() != null && genero.getValue() != null && !especie.getValue().isEmpty() &&
                    !autorAno.getValue().isEmpty() && fechaAlta.getValue() != null && !numeroFrasco.getValue().isEmpty()) {
                Especie duplicado = new Especie();
                String frascoString = especieService.nuevoFrasco(numeroCatalogo.getValue());
                Integer numero = Integer.parseInt(frascoString) + 1;
                switch (numero.toString().length()) {
                    case 1:
                        duplicado.setNumeroFrasco("0" + numero.toString());
                        break;
                    case 2:
                        duplicado.setNumeroFrasco(numero.toString());
                        break;
                }
                duplicado.setGenero(genero.getValue());
                duplicado.setNumeroCatalogo(numeroCatalogo.getValue());
                duplicado.setEspecie(especie.getValue());
                duplicado.setFechaAlta(fechaAlta.getValue());
                duplicado.setAutorAno(autorAno.getValue());
                especieService.guardar(duplicado);
                numeroFrasco.setValue(duplicado.getNumeroFrasco());
                Notification.show("Duplicado", 3000, Notification.Position.MIDDLE);

                //Copiar todos los formularios
            } else
                Notification.show("Comprueba tus datos al duplicar", 3000, Notification.Position.MIDDLE);
        });

        HorizontalLayout numeroCatalogoLay = new HorizontalLayout(numeroCatalogo, numeroFrasco, comprobar, nuevo);
        numeroCatalogoLay.setAlignItems(FlexComponent.Alignment.BASELINE);
        HorizontalLayout phylumLay = new HorizontalLayout(phylum, addThingsPhylum);
        phylumLay.setAlignItems(FlexComponent.Alignment.BASELINE);
        HorizontalLayout categoriaLay = new HorizontalLayout(categoriaTaxonomicaPpal, addThingsCategoria);
        categoriaLay.setAlignItems(FlexComponent.Alignment.BASELINE);
        HorizontalLayout familiaLay = new HorizontalLayout(familia, addThingsFamilia);
        familiaLay.setAlignItems(FlexComponent.Alignment.BASELINE);
        HorizontalLayout generoLay = new HorizontalLayout(genero, addThingsGenero);
        generoLay.setAlignItems(FlexComponent.Alignment.BASELINE);
        HorizontalLayout especieLay = new HorizontalLayout(especie, autorAno, fechaAlta);
        VerticalLayout izq = new VerticalLayout(numeroCatalogoLay, categoriaLay, generoLay);
        VerticalLayout dcha = new VerticalLayout(phylumLay, familiaLay, especieLay, save, duplicar);

        add(izq, dcha);
        //save.addClickShortcut(Key.ENTER);
        save.addClickListener(event -> save());
    }

    public void save(){
        if (nuevo.getValue()) { //nuevo registro
            if (!numeroCatalogo.getValue().isEmpty() && phylum.getValue() != null && !numeroFrasco.getValue().isEmpty()) {
                Especie e = new Especie();
                e.setEspecie(especie.getValue());
                e.setCategoriaTaxonomicaPpal(categoriaTaxonomicaPpal.getValue());
                e.setPhylum(phylum.getValue());
                e.setFamilia(familia.getValue());
                e.setAutorAno(autorAno.getValue());
                e.setFechaAlta(fechaAlta.getValue());
                e.setNumeroCatalogo(numeroCatalogo.getValue());
                e.setNumeroFrasco(numeroFrasco.getValue());
                e.setGenero(genero.getValue());
                especieService.guardar(e);
                Notification.show("Guardado con éxito", 3000, Notification.Position.MIDDLE);
            } else
                Notification.show("Comprueba tus datos", 3000, Notification.Position.MIDDLE);
        } else { //registro antiguo
            if (!numeroCatalogo.getValue().isEmpty() && phylum.getValue() != null && !numeroFrasco.getValue().isEmpty()) {
                especieCreada.setEspecie(especie.getValue());
                especieCreada.setAutorAno(autorAno.getValue());
                especieCreada.setCategoriaTaxonomicaPpal(categoriaTaxonomicaPpal.getValue());
                especieCreada.setPhylum(phylum.getValue());
                especieCreada.setFamilia(familia.getValue());
                especieCreada.setFechaAlta(fechaAlta.getValue());
                especieCreada.setNumeroCatalogo(numeroCatalogo.getValue());
                especieCreada.setNumeroFrasco(numeroFrasco.getValue());
                especieCreada.setGenero(genero.getValue());
                especieService.guardar(especieCreada);
                Notification.show("Guardado con éxito", 3000, Notification.Position.MIDDLE);
            } else
                Notification.show("Comprueba tus datos", 3000, Notification.Position.MIDDLE);
        }
    }
}

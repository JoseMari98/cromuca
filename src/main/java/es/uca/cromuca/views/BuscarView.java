package es.uca.cromuca.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.entities.*;
import es.uca.cromuca.entities.enums.TipoSustrato;
import es.uca.cromuca.services.*;
import es.uca.cromuca.services.enums.TipoSustratoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

@Route(value = "BuscarView", layout = MainView.class)
public class BuscarView extends VerticalLayout {
    private Grid<Especie> grid = new Grid<>(Especie.class);
    private Button comprobar = new Button("Buscar");
    private TextField numeroCatalogo = new TextField("Num. catálogo");
    private ComboBox<Especie> especie = new ComboBox<>("Especie");
    private ComboBox<Phylum> phylumComboBox = new ComboBox<>("Phylum");
    private ComboBox<CategoriaTaxonomicaPpal> categoriaTaxonomicaPpalComboBox = new ComboBox<>("Categoría Taxonómica");
    private ComboBox<Familia> familiaComboBox = new ComboBox<>("Familia");
    private ComboBox<Genero> generoComboBox = new ComboBox<>("Género");
    private ComboBox<String> regionMarina = new ComboBox<>("Región marina");
    private ComboBox<String> regionBiogeografica = new ComboBox<>("Región biogeográfica");
    private ComboBox<TipoSustrato> tipoSustrato = new ComboBox<>("Tipo sustrato");
    private ComboBox<String> tipoOrganismo = new ComboBox<>("Tipo organismo");
    //private ComboBox<String> conservanteActual = new ComboBox<>();
    private ComboBox<String> habitat = new ComboBox<>("Habitat");
    private PhylumService phylumService;
    private List<Especie> especieList = new LinkedList<>();

    @Autowired
    public BuscarView(PhylumService phylumService, CategoriaTaxonomicaService categoriaTaxonomicaService, GeneroService generoService, EspecieService especieService, FamiliaService familiaService,
                      DatosMuestreoService datosMuestreoService, TipoSustratoService tipoSustratoService) {
        this.phylumService = phylumService;

        grid.setColumns();
        grid.addColumn(Especie -> Especie.getNumeroCatalogo() + " " + Especie.getNumeroFrasco()).setHeader("Núm catálogo").setSortable(true);
        grid.addColumn(Especie -> Especie.getPhylum() == null ? "Sin phylum" : Especie.getPhylum().getPhylum()).setHeader("Phylum").setSortable(true);
        grid.addColumn(Especie -> Especie.getCategoriaTaxonomicaPpal() == null ? "Sin categoría" : Especie.getCategoriaTaxonomicaPpal().getCategoriaTaxonomicaPpal()).setHeader("Categoría").setSortable(true);
        grid.addColumn(Especie -> Especie.getFamilia() == null ? "Sin familia" : Especie.getFamilia().getFamilia()).setHeader("Familia").setSortable(true);
        grid.addColumn(Especie -> Especie.getGenero() == null ? "Sin género" : Especie.getGenero().getGenero()).setHeader("Género").setSortable(true);
        grid.addColumn(Especie -> Especie.getDatosMuestreo() == null ? "Sin datos" : Especie.getDatosMuestreo().getTipoSustrato()).setHeader("Sustrato").setSortable(true);
        grid.addColumn(Especie -> Especie.getDatosMuestreo() == null ? "Sin datos" : Especie.getDatosMuestreo().getRegionBiogeografica()).setHeader("Region biogeografica").setSortable(true);
        grid.addColumn(Especie -> Especie.getDatosMuestreo() == null ? "Sin datos" : Especie.getDatosMuestreo().getRegionMarina()).setHeader("Region marina").setSortable(true);
        grid.addColumn(Especie -> Especie.getDatosMuestreo() == null ? "Sin datos" : Especie.getDatosMuestreo().getHabitat()).setHeader("Habitat").setSortable(true);
        grid.addColumn(Especie -> Especie.getDatosMuestreo() == null ? "Sin datos" : Especie.getDatosMuestreo().getTipoOrganismo()).setHeader("Tipo organismo").setSortable(true);
        grid.addColumn(Especie::getEspecie).setHeader("Especie").setSortable(true);


        HorizontalLayout formularios = new HorizontalLayout(numeroCatalogo, phylumComboBox, categoriaTaxonomicaPpalComboBox, familiaComboBox, generoComboBox, especie, comprobar);
        HorizontalLayout datosMuestreo = new HorizontalLayout(regionBiogeografica, regionMarina, habitat, tipoOrganismo, tipoSustrato);
        formularios.setAlignItems(Alignment.BASELINE);
        HorizontalLayout mainContent = new HorizontalLayout(grid); //metemos en un objeto el grid y formulario
        mainContent.setSizeFull();
        grid.setSizeFull();

        comprobar.addClickListener(e -> {
            if (!numeroCatalogo.getValue().isEmpty()) {
                if (especieService.findByNumeroCatalogoOrderByNumeroFrascoDesc(numeroCatalogo.getValue()) != null) {
                    especieList.clear();
                    especieList = especieService.findByNumeroCatalogoOrderByNumeroFrascoDesc(numeroCatalogo.getValue());
                    this.updateList();
                } else {
                    Notification.show("No se encontraron resultados", 3000, Notification.Position.MIDDLE);
                }
            } else {
                if (phylumComboBox.getValue() != null || categoriaTaxonomicaPpalComboBox.getValue() != null || familiaComboBox.getValue() != null || generoComboBox.getValue() != null || especie.getValue() != null) {
                    if (especieService.find(phylumComboBox.getValue(), categoriaTaxonomicaPpalComboBox.getValue(), familiaComboBox.getValue(), generoComboBox.getValue(), especie.getValue()) != null) {
                        especieList.clear();
                        especieList = especieService.find(phylumComboBox.getValue(), categoriaTaxonomicaPpalComboBox.getValue(), familiaComboBox.getValue(),
                                generoComboBox.getValue(), especie.getValue());
                        if (regionBiogeografica.getValue() != null || regionMarina.getValue() != null || habitat.getValue() != null || tipoSustrato.getValue() != null) {
                            List<DatosMuestreo> datosMuestreoList = datosMuestreoService.find(tipoOrganismo.getValue(), regionMarina.getValue(), regionBiogeografica.getValue(), habitat.getValue(), tipoSustrato.getValue());
                            especieList = datosMuestreoService.concatenarListas(especieList, datosMuestreoList);
                        }
                        this.updateList();
                    } else {
                        Notification.show("No se encontraron resultados", 3000, Notification.Position.MIDDLE);
                    }
                } else {
                    if (!regionBiogeografica.getValue().isEmpty() || !regionMarina.getValue().isEmpty() || !habitat.getValue().isEmpty() || tipoSustrato.getValue() != null) {
                        List<DatosMuestreo> datosMuestreoList = datosMuestreoService.find(tipoOrganismo.getValue(), regionMarina.getValue(), regionBiogeografica.getValue(), habitat.getValue(), tipoSustrato.getValue());
                        especieList.clear();
                        especieList = datosMuestreoService.concatenarListas(especieList, datosMuestreoList);
                        updateList();
                    } else
                        Notification.show("No se encontraron resultados", 3000, Notification.Position.MIDDLE);

                }
            }
        });

        phylumComboBox.setItems(phylumService.findAll());
        phylumComboBox.setItemLabelGenerator(Phylum::getPhylum);
        phylumComboBox.addValueChangeListener(e -> {
            if (phylumComboBox.getValue() != null) {
                categoriaTaxonomicaPpalComboBox.setItems(categoriaTaxonomicaService.findByPhylum(phylumComboBox.getValue()));
                categoriaTaxonomicaPpalComboBox.setItemLabelGenerator(CategoriaTaxonomicaPpal::getCategoriaTaxonomicaPpal);
            }
        });
        categoriaTaxonomicaPpalComboBox.addValueChangeListener(e -> {
            if (categoriaTaxonomicaPpalComboBox.getValue() != null) {
                familiaComboBox.setItems(familiaService.findByCategoria(categoriaTaxonomicaPpalComboBox.getValue()));
                familiaComboBox.setItemLabelGenerator(Familia::getFamilia);
            }
        });
        familiaComboBox.addValueChangeListener(e -> {
            if (familiaComboBox.getValue() != null) {
                generoComboBox.setItems(generoService.findByFamilia(familiaComboBox.getValue()));
                generoComboBox.setItemLabelGenerator(Genero::getGenero);
            }
        });
        especie.addValueChangeListener(e -> {
            if (generoComboBox.getValue() != null) {
                especie.setItems(especieService.findByGenero(generoComboBox.getValue()));
                especie.setItemLabelGenerator(Especie::getEspecie);
            }
        });

        regionMarina.setItems(DatosMuestreo.regionMarinaList());
        regionBiogeografica.setItems(DatosMuestreo.regionBiogeograficaList());
        habitat.setItems(DatosMuestreo.habitatList());
        tipoSustrato.setItems(tipoSustratoService.findAll());
        tipoOrganismo.setItems(DatosMuestreo.tiposOrganismosList());

        add(formularios, datosMuestreo, mainContent);

        setSizeFull();

        updateList();

        grid.asSingleSelect().addValueChangeListener(event -> {
            UI.getCurrent().getSession().setAttribute(Especie.class, grid.asSingleSelect().getValue());
            UI.getCurrent().navigate("DatosMuestreoView");
        });
    }

    public void updateList() {
        grid.setItems(especieList);
    }
}
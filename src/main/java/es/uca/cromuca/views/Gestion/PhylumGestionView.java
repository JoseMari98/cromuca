package es.uca.cromuca.views.Gestion;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.entities.Phylum;
import es.uca.cromuca.forms.Gestion.PhylumGestionForm;
import es.uca.cromuca.services.*;
import es.uca.cromuca.views.AbstractView;
import es.uca.cromuca.views.MainView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "GestionPhylum", layout = MainView.class)
public class PhylumGestionView extends AbstractView {
    private Grid<Phylum> grid = new Grid<>(Phylum.class);
    private TextField filterText = new TextField();
    private PhylumService phylumService;
    private PhylumGestionForm form;

    @Autowired
    public PhylumGestionView(PhylumService phylumService, CategoriaTaxonomicaService categoriaTaxonomicaService, GeneroService generoService, EspecieService especieService, FamiliaService familiaService) {
        this.form = new PhylumGestionForm(this, generoService, especieService, familiaService, categoriaTaxonomicaService, phylumService);
        this.phylumService = phylumService;

        filterText.setPlaceholder("Filtrar por phylum"); //poner el campo
        filterText.setClearButtonVisible(true); //poner la cruz para borrar
        filterText.setValueChangeMode(ValueChangeMode.EAGER); //que se hagan los cambios cuando se escriba
        filterText.addValueChangeListener(event -> {
            if (phylumService.findByPhylum(filterText.getValue()) != null)
                updateList();
            else {
                filterText.clear();
                Notification.show("No hay ningún phylum con ese nombre", 2000, Notification.Position.MIDDLE);
            }

        });
        Button addModeloBtn = new Button("Añade un phylum");
        addModeloBtn.addClickListener(e -> {
            grid.asSingleSelect().clear(); //clear para que borre si habia algo antes
            form.setPhylum(new Phylum()); //instancia un nuevo customer
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addModeloBtn);

        grid.setColumns("phylum");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form); //metemos en un objeto el grid y formulario
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();

        updateList();

        form.setPhylum(null);

        grid.asSingleSelect().addValueChangeListener(event -> form.setPhylum(grid.asSingleSelect().getValue()));
    }

    public void updateList() {
        if (filterText.isEmpty())
            grid.setItems(phylumService.findAll());
        else
            grid.setItems(phylumService.findByPhylum(filterText.getValue()));
    }
}

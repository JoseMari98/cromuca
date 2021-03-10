package es.uca.cromuca.views.Gestion;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.entities.CategoriaTaxonomicaPpal;
import es.uca.cromuca.forms.Gestion.CategoriaTaxonomicaGestionForm;
import es.uca.cromuca.services.*;
import es.uca.cromuca.views.AbstractView;
import es.uca.cromuca.views.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Secured("Admin")
@Route(value = "GestionCategoria", layout = MainView.class)
public class CategoriaTaxonomicaGestionView extends AbstractView {
    private Grid<CategoriaTaxonomicaPpal> grid = new Grid<>(CategoriaTaxonomicaPpal.class);
    private TextField filterText = new TextField();
    private CategoriaTaxonomicaService categoriaTaxonomicaService;
    private CategoriaTaxonomicaGestionForm form;

    @Autowired
    public CategoriaTaxonomicaGestionView(PhylumService phylumService, CategoriaTaxonomicaService categoriaTaxonomicaService, GeneroService generoService, EspecieService especieService, FamiliaService familiaService) {
        this.form = new CategoriaTaxonomicaGestionForm(this, generoService, especieService, familiaService, categoriaTaxonomicaService, phylumService);
        this.categoriaTaxonomicaService = categoriaTaxonomicaService;

        filterText.setPlaceholder("Filtrar por categoría"); //poner el campo
        filterText.setClearButtonVisible(true); //poner la cruz para borrar
        filterText.setValueChangeMode(ValueChangeMode.EAGER); //que se hagan los cambios cuando se escriba
        filterText.addValueChangeListener(event -> {
            if (categoriaTaxonomicaService.findByCategoriaTaxonomica(filterText.getValue()) != null)
                updateList();
            else {
                filterText.clear();
                Notification.show("No hay ninguna cateogoría con ese nombre", 2000, Notification.Position.MIDDLE);
            }

        });
        Button addModeloBtn = new Button("Añade una categoría");
        addModeloBtn.addClickListener(e -> {
            grid.asSingleSelect().clear(); //clear para que borre si habia algo antes
            form.setCategoriaTaxonomicaPpal(new CategoriaTaxonomicaPpal()); //instancia un nuevo customer
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addModeloBtn);

        grid.setColumns("phylum.phylum", "categoriaTaxonomicaPpal");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form); //metemos en un objeto el grid y formulario
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();

        updateList();

        form.setCategoriaTaxonomicaPpal(null);

        grid.asSingleSelect().addValueChangeListener(event -> form.setCategoriaTaxonomicaPpal(grid.asSingleSelect().getValue()));
    }

    public void updateList() {
        if (filterText.isEmpty())
            grid.setItems(categoriaTaxonomicaService.findAll());
        else
            grid.setItems(categoriaTaxonomicaService.findByCategoriaTaxonomica(filterText.getValue()));
    }
}

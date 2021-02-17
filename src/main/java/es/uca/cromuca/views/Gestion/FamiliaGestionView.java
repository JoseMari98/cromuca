package es.uca.cromuca.views.Gestion;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.entities.Familia;
import es.uca.cromuca.forms.Gestion.FamiliaGestionForm;
import es.uca.cromuca.services.*;
import es.uca.cromuca.views.AbstractView;
import es.uca.cromuca.views.MainView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "GestionFamilia", layout = MainView.class)
public class FamiliaGestionView extends AbstractView {
    private Grid<Familia> grid = new Grid<>(Familia.class);
    private TextField filterText = new TextField();
    private FamiliaService familiaService;
    private FamiliaGestionForm form;

    @Autowired
    public FamiliaGestionView(CategoriaTaxonomicaService categoriaTaxonomicaService, GeneroService generoService, EspecieService especieService, FamiliaService familiaService, PhylumService phylumService) {
        this.form = new FamiliaGestionForm(this, generoService, especieService, familiaService, categoriaTaxonomicaService, phylumService);
        this.familiaService = familiaService;

        filterText.setPlaceholder("Filtrar por tipo"); //poner el campo
        filterText.setClearButtonVisible(true); //poner la cruz para borrar
        filterText.setValueChangeMode(ValueChangeMode.EAGER); //que se hagan los cambios cuando se escriba
        filterText.addValueChangeListener(event -> {
            if (familiaService.findByFamilia(filterText.getValue()) != null)
                updateList();
            else {
                filterText.clear();
                Notification.show("No hay ningun vehiculo con esa matricula", 2000, Notification.Position.MIDDLE);
            }

        });
        Button addModeloBtn = new Button("Añade un tipo");
        addModeloBtn.addClickListener(e -> {
            grid.asSingleSelect().clear(); //clear para que borre si habia algo antes
            form.setFamilia(new Familia()); //instancia un nuevo customer
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addModeloBtn);

        grid.setColumns("categoriaTaxonomicaPpal.categoriaTaxonomicaPpal", "familia");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form); //metemos en un objeto el grid y formulario
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();

        updateList();

        form.setFamilia(null);

        grid.asSingleSelect().addValueChangeListener(event -> form.setFamilia(grid.asSingleSelect().getValue()));
    }

    public void updateList() {
        if (filterText.isEmpty())
            grid.setItems(familiaService.findAll());
        else
            grid.setItems(familiaService.findByFamilia(filterText.getValue()));
    }
}

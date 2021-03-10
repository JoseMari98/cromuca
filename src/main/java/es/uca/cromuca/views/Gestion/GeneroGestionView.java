package es.uca.cromuca.views.Gestion;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.entities.Genero;
import es.uca.cromuca.forms.Gestion.GeneroGestionForm;
import es.uca.cromuca.services.EspecieService;
import es.uca.cromuca.services.FamiliaService;
import es.uca.cromuca.services.GeneroService;
import es.uca.cromuca.views.AbstractView;
import es.uca.cromuca.views.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Secured("Admin")
@Route(value = "GestionGenero", layout = MainView.class)
public class GeneroGestionView extends AbstractView {
    private Grid<Genero> grid = new Grid<>(Genero.class);
    private TextField filterText = new TextField();
    private GeneroService generoService;
    private GeneroGestionForm form;

    @Autowired
    public GeneroGestionView(GeneroService generoService, EspecieService especieService, FamiliaService familiaService) {
        this.form = new GeneroGestionForm(this, generoService, especieService, familiaService);
        this.generoService = generoService;

        filterText.setPlaceholder("Filtrar por género"); //poner el campo
        filterText.setClearButtonVisible(true); //poner la cruz para borrar
        filterText.setValueChangeMode(ValueChangeMode.EAGER); //que se hagan los cambios cuando se escriba
        filterText.addValueChangeListener(event -> {
            if (generoService.findByGenero(filterText.getValue()) != null)
                updateList();
            else {
                filterText.clear();
                Notification.show("No hay ningún género con ese nombre", 2000, Notification.Position.MIDDLE);
            }

        });
        Button addModeloBtn = new Button("Añade un género");
        addModeloBtn.addClickListener(e -> {
            grid.asSingleSelect().clear(); //clear para que borre si habia algo antes
            form.setGenero(new Genero()); //instancia un nuevo customer
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addModeloBtn);

        grid.setColumns("familia.familia", "genero");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form); //metemos en un objeto el grid y formulario
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();

        updateList();

        form.setGenero(null);

        grid.asSingleSelect().addValueChangeListener(event -> form.setGenero(grid.asSingleSelect().getValue()));
    }

    public void updateList() {
        if (filterText.isEmpty())
            grid.setItems(generoService.findAll());
        else
            grid.setItems(generoService.findByGenero(filterText.getValue()));
    }
}

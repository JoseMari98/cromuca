package es.uca.cromuca.views.Gestion;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.entities.enums.Pais;
import es.uca.cromuca.forms.Gestion.PaisGestionForm;
import es.uca.cromuca.services.enums.PaisService;
import es.uca.cromuca.views.AbstractView;
import es.uca.cromuca.views.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Secured("Admin")
@Route(value = "GestionPais", layout = MainView.class)
public class PaisGestionView extends AbstractView {
    private Grid<Pais> grid = new Grid<>(Pais.class);
    private TextField filterText = new TextField();
    private PaisService paisService;
    private PaisGestionForm form;

    @Autowired
    public PaisGestionView(PaisService paisService) {
        this.form = new PaisGestionForm(this, paisService);
        this.paisService = paisService;

        filterText.setPlaceholder("Filtrar por pais"); //poner el campo
        filterText.setClearButtonVisible(true); //poner la cruz para borrar
        filterText.setValueChangeMode(ValueChangeMode.EAGER); //que se hagan los cambios cuando se escriba
        filterText.addValueChangeListener(event -> {
            if (paisService.findByPais(filterText.getValue()) != null)
                updateList();
            else {
                filterText.clear();
                Notification.show("No hay ningún pais con ese nombre", 2000, Notification.Position.MIDDLE);
            }

        });
        Button addModeloBtn = new Button("Añade un pais");
        addModeloBtn.addClickListener(e -> {
            grid.asSingleSelect().clear(); //clear para que borre si habia algo antes
            form.setPais(new Pais()); //instancia un nuevo customer
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addModeloBtn);

        grid.setColumns("pais");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form); //metemos en un objeto el grid y formulario
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();

        updateList();

        form.setPais(null);

        grid.asSingleSelect().addValueChangeListener(event -> form.setPais(grid.asSingleSelect().getValue()));
    }

    public void updateList() {
        if (filterText.isEmpty())
            grid.setItems(paisService.findAll());
        else
            grid.setItems(paisService.findByPais(filterText.getValue()));
    }
}

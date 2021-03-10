package es.uca.cromuca.views.Gestion;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.entities.enums.TipoSustrato;
import es.uca.cromuca.forms.Gestion.TipoSustratoGestionForm;
import es.uca.cromuca.services.enums.TipoSustratoService;
import es.uca.cromuca.views.AbstractView;
import es.uca.cromuca.views.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Secured("Admin")
@Route(value = "GestionTipoSustrato", layout = MainView.class)
public class TipoSustratoGestionView extends AbstractView {
    private Grid<TipoSustrato> grid = new Grid<>(TipoSustrato.class);
    private TextField filterText = new TextField();
    private TipoSustratoService tipoSustratoService;
    private TipoSustratoGestionForm form;

    @Autowired
    public TipoSustratoGestionView(TipoSustratoService tipoSustratoService) {
        this.form = new TipoSustratoGestionForm(this, tipoSustratoService);
        this.tipoSustratoService = tipoSustratoService;

        filterText.setPlaceholder("Filtrar por metodo captura"); //poner el campo
        filterText.setClearButtonVisible(true); //poner la cruz para borrar
        filterText.setValueChangeMode(ValueChangeMode.EAGER); //que se hagan los cambios cuando se escriba
        filterText.addValueChangeListener(event -> {
            if (tipoSustratoService.findByTipoSustrato(filterText.getValue()) != null)
                updateList();
            else {
                filterText.clear();
                Notification.show("No hay ningún metodo captura con ese nombre", 2000, Notification.Position.MIDDLE);
            }

        });
        Button addModeloBtn = new Button("Añade un metodo captura");
        addModeloBtn.addClickListener(e -> {
            grid.asSingleSelect().clear(); //clear para que borre si habia algo antes
            form.setTipoSustrato(new TipoSustrato()); //instancia un nuevo customer
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addModeloBtn);

        grid.setColumns("tipoSustrato");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form); //metemos en un objeto el grid y formulario
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();

        updateList();

        form.setTipoSustrato(null);

        grid.asSingleSelect().addValueChangeListener(event -> form.setTipoSustrato(grid.asSingleSelect().getValue()));
    }

    public void updateList() {
        if (filterText.isEmpty())
            grid.setItems(tipoSustratoService.findAll());
        else
            grid.setItems(tipoSustratoService.findByTipoSustrato(filterText.getValue()));
    }
}

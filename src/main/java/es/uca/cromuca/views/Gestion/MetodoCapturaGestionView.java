package es.uca.cromuca.views.Gestion;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.entities.enums.MetodoCaptura;
import es.uca.cromuca.forms.Gestion.MetodoCapturaGestionForm;
import es.uca.cromuca.services.enums.MetodoCapturaService;
import es.uca.cromuca.views.AbstractView;
import es.uca.cromuca.views.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Secured("Admin")
@Route(value = "GestionMetodoCaptura", layout = MainView.class)
public class MetodoCapturaGestionView extends AbstractView {
    private Grid<MetodoCaptura> grid = new Grid<>(MetodoCaptura.class);
    private TextField filterText = new TextField();
    private MetodoCapturaService metodoCaptura;
    private MetodoCapturaGestionForm form;

    @Autowired
    public MetodoCapturaGestionView(MetodoCapturaService metodoCapturaService) {
        this.form = new MetodoCapturaGestionForm(this, metodoCapturaService);
        this.metodoCaptura = metodoCapturaService;

        filterText.setPlaceholder("Filtrar por metodo captura"); //poner el campo
        filterText.setClearButtonVisible(true); //poner la cruz para borrar
        filterText.setValueChangeMode(ValueChangeMode.EAGER); //que se hagan los cambios cuando se escriba
        filterText.addValueChangeListener(event -> {
            if (metodoCapturaService.findByMetodoCaptura(filterText.getValue()) != null)
                updateList();
            else {
                filterText.clear();
                Notification.show("No hay ningún metodo captura con ese nombre", 2000, Notification.Position.MIDDLE);
            }

        });
        Button addModeloBtn = new Button("Añade un metodo captura");
        addModeloBtn.addClickListener(e -> {
            grid.asSingleSelect().clear(); //clear para que borre si habia algo antes
            form.setMetodoCaptura(new MetodoCaptura()); //instancia un nuevo customer
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addModeloBtn);

        grid.setColumns("metodoCaptura");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form); //metemos en un objeto el grid y formulario
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();

        updateList();

        form.setMetodoCaptura(null);

        grid.asSingleSelect().addValueChangeListener(event -> form.setMetodoCaptura(grid.asSingleSelect().getValue()));
    }

    public void updateList() {
        if (filterText.isEmpty())
            grid.setItems(metodoCaptura.findAll());
        else
            grid.setItems(metodoCaptura.findByMetodoCaptura(filterText.getValue()));
    }
}

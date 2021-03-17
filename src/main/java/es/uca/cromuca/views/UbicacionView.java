package es.uca.cromuca.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.entities.Prestamo;
import es.uca.cromuca.entities.Ubicacion;
import es.uca.cromuca.forms.PrestamoForm;
import es.uca.cromuca.forms.UbicacionForm;
import es.uca.cromuca.services.EspecieService;
import es.uca.cromuca.services.PrestamoService;
import es.uca.cromuca.services.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Secured("Admin")
@Route(value = "UbicacionView", layout = MainView.class)
public class UbicacionView extends AbstractView {
    private UbicacionForm ubicacionForm;
    private H1 titulo = new H1("Historial determinacion");
    private PrestamoForm prestamoForm;
    private Grid<Prestamo> prestamoGrid = new Grid<>(Prestamo.class);
    private PrestamoService prestamoService;

    @Autowired
    UbicacionView(EspecieService especieService, UbicacionService ubicacionService, PrestamoService prestamoService) {
        this.ubicacionForm = new UbicacionForm(this, especieService, ubicacionService);
        this.prestamoForm = new PrestamoForm(this, ubicacionService, prestamoService);
        this.prestamoService = prestamoService;

        VerticalLayout contenido = new VerticalLayout(titulo, ubicacionForm);
        contenido.setSizeFull();
        //setSizeFull();
        Button addModeloBtn = new Button("Añade un préstamo");
        addModeloBtn.addClickListener(e -> {
            if (UI.getCurrent().getSession().getAttribute(Ubicacion.class) != null) {
                prestamoGrid.asSingleSelect().clear(); //clear para que borre si habia algo antes
                prestamoForm.setPrestamo(new Prestamo());
            } else
                Notification.show("No hay numero de catalogo buscado", 3000, Notification.Position.MIDDLE);
        });

        HorizontalLayout mainContent = new HorizontalLayout(prestamoGrid, prestamoForm); //metemos en un objeto el grid y formulario
        mainContent.setSizeFull();
        prestamoGrid.setColumns("nombre", "fecha", "numEjemplares", "institucion", "email", "telefono", "devuelto", "fechaLimite", "observaciones");

        updateList();

        add(contenido, addModeloBtn, mainContent);

        prestamoForm.setPrestamo(null);

        prestamoGrid.asSingleSelect().addValueChangeListener(event -> prestamoForm.setPrestamo(prestamoGrid.asSingleSelect().getValue()));

        UI.getCurrent().addBeforeLeaveListener(e -> UI.getCurrent().getSession().setAttribute(Ubicacion.class, null));
    }

    public void updateList() {
        if (UI.getCurrent().getSession().getAttribute(Ubicacion.class) != null)
            prestamoGrid.setItems(prestamoService.findByUbicacion(UI.getCurrent().getSession().getAttribute(Ubicacion.class)));
    }
}

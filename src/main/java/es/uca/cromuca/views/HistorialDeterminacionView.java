package es.uca.cromuca.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.forms.HistorialDeterminacionForm;
import es.uca.cromuca.services.EspecieService;
import es.uca.cromuca.services.HistorialDeterminacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Secured("Admin")
@Route(value = "HistorialDeterminacionView", layout = MainView.class)
public class HistorialDeterminacionView extends AbstractView {
    private HistorialDeterminacionForm historialDeterminacionForm;
    private H1 titulo = new H1("Historial determinacion");

    @Autowired
    HistorialDeterminacionView(EspecieService especieService, HistorialDeterminacionService historialDeterminacionService) {
        this.historialDeterminacionForm = new HistorialDeterminacionForm(this, especieService, historialDeterminacionService);
        VerticalLayout contenido = new VerticalLayout(titulo, historialDeterminacionForm);
        //contenido.setSizeFull();
        add(contenido);
        //setSizeFull();
    }
}

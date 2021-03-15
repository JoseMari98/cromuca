package es.uca.cromuca.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.forms.Gestion.EjemplaresForm;
import es.uca.cromuca.services.EjemplaresService;
import es.uca.cromuca.services.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Secured("Admin")
@Route(value = "EjemplaresView", layout = MainView.class)
public class EjemplaresView extends AbstractView {
    private EjemplaresForm ejemplaresForm;
    private H1 titulo = new H1("Ejemplares");

    @Autowired
    EjemplaresView(EspecieService especieService, EjemplaresService ejemplaresService) {
        this.ejemplaresForm = new EjemplaresForm(this, especieService, ejemplaresService);
        VerticalLayout contenido = new VerticalLayout(titulo, ejemplaresForm);
        //contenido.setSizeFull();
        add(contenido);
        //setSizeFull();
    }
}

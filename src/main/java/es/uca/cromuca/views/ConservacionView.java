package es.uca.cromuca.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.forms.ConservacionForm;
import es.uca.cromuca.services.ConservacionService;
import es.uca.cromuca.services.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Secured("Admin")
@Route(value = "ConservacionView", layout = MainView.class)
public class ConservacionView extends AbstractView {
    private ConservacionForm conservacionForm;
    private H1 titulo = new H1("Ejemplares");

    @Autowired
    ConservacionView(EspecieService especieService, ConservacionService conservacionService) {
        this.conservacionForm = new ConservacionForm(this, especieService, conservacionService);
        VerticalLayout contenido = new VerticalLayout(titulo, conservacionForm);
        //contenido.setSizeFull();
        add(contenido);
        //setSizeFull();
    }
}

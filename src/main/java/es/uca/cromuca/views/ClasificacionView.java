package es.uca.cromuca.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.forms.ClasificacionForm;
import es.uca.cromuca.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Secured("Admin")
@Route(value = "ClasificacionView", layout = MainView.class)
public class ClasificacionView extends AbstractView {
    private ClasificacionForm clasificacionForm;

    @Autowired
    ClasificacionView(GeneroService generoService, CategoriaTaxonomicaService categoriaTaxonomicaService, FamiliaService familiaService, PhylumService phylumService,
                      EspecieService especieService) {
        this.clasificacionForm = new ClasificacionForm(generoService, especieService, familiaService, categoriaTaxonomicaService, phylumService, true);
        VerticalLayout contenido = new VerticalLayout(clasificacionForm);
        //contenido.setSizeFull();
        add(contenido);
        //setSizeFull();
    }
}

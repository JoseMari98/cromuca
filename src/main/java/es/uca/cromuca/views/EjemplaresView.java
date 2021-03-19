package es.uca.cromuca.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.forms.ClasificacionForm;
import es.uca.cromuca.forms.EjemplaresForm;
import es.uca.cromuca.services.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "EjemplaresView", layout = MainView.class)
public class EjemplaresView extends VerticalLayout {
    private EjemplaresForm ejemplaresForm;
    private H1 titulo = new H1("Ejemplares");
    private ClasificacionForm clasificacionForm;

    @Autowired
    EjemplaresView(GeneroService generoService, CategoriaTaxonomicaService categoriaTaxonomicaService, FamiliaService familiaService, PhylumService phylumService,
                   EspecieService especieService, EjemplaresService ejemplaresService) {
        this.ejemplaresForm = new EjemplaresForm(this, especieService, ejemplaresService);
        this.clasificacionForm = new ClasificacionForm(generoService, especieService, familiaService, categoriaTaxonomicaService, phylumService);
        this.clasificacionForm.ejemplaresForm = ejemplaresForm;
        VerticalLayout contenido = new VerticalLayout(clasificacionForm, ejemplaresForm);
        //contenido.setSizeFull();
        add(contenido);
        //setSizeFull();
    }
}

package es.uca.cromuca.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.forms.ClasificacionForm;
import es.uca.cromuca.services.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "ClasificacionView", layout = MainView.class)
public class ClasificacionView extends AbstractView{
    private ClasificacionForm clasificacionForm;
    private H1 titulo = new H1("Clasificación");

    @Autowired
    ClasificacionView(CategoriaTaxonomicaService categoriaTaxonomicaService, GeneroService generoService, EspecieService especieService, FamiliaService familiaService, PhylumService phylumService){
        this.clasificacionForm = new ClasificacionForm(this, generoService, especieService, familiaService, categoriaTaxonomicaService, phylumService);
        VerticalLayout contenido = new VerticalLayout(titulo, clasificacionForm);
        contenido.setSizeFull();
        add(contenido);
        setSizeFull();
    }
}

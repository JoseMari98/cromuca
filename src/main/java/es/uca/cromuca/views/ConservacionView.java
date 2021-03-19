package es.uca.cromuca.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.forms.ClasificacionForm;
import es.uca.cromuca.forms.ConservacionForm;
import es.uca.cromuca.services.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "ConservacionView", layout = MainView.class)
public class ConservacionView extends VerticalLayout {
    private ConservacionForm conservacionForm;
    private ClasificacionForm clasificacionForm;

    @Autowired
    ConservacionView(GeneroService generoService, CategoriaTaxonomicaService categoriaTaxonomicaService, FamiliaService familiaService, PhylumService phylumService,
                     EspecieService especieService, ConservacionService conservacionService) {
        this.conservacionForm = new ConservacionForm(this, especieService, conservacionService);
        this.clasificacionForm = new ClasificacionForm(generoService, especieService, familiaService, categoriaTaxonomicaService, phylumService);
        this.clasificacionForm.conservacionForm = conservacionForm;
        VerticalLayout contenido = new VerticalLayout(clasificacionForm, conservacionForm);
        //contenido.setSizeFull();
        add(contenido);
        //setSizeFull();
    }
}

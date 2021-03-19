package es.uca.cromuca.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.forms.ClasificacionForm;
import es.uca.cromuca.forms.HistorialDeterminacionForm;
import es.uca.cromuca.services.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "HistorialDeterminacionView", layout = MainView.class)
public class HistorialDeterminacionView extends VerticalLayout {
    private HistorialDeterminacionForm historialDeterminacionForm;
    private ClasificacionForm clasificacionForm;

    @Autowired
    HistorialDeterminacionView(GeneroService generoService, CategoriaTaxonomicaService categoriaTaxonomicaService, FamiliaService familiaService, PhylumService phylumService,
                               EspecieService especieService, HistorialDeterminacionService historialDeterminacionService) {
        this.historialDeterminacionForm = new HistorialDeterminacionForm(this, especieService, historialDeterminacionService);
        this.clasificacionForm = new ClasificacionForm(generoService, especieService, familiaService, categoriaTaxonomicaService, phylumService);
        this.clasificacionForm.historialDeterminacionForm = historialDeterminacionForm;
        VerticalLayout contenido = new VerticalLayout(clasificacionForm, historialDeterminacionForm);
        //contenido.setSizeFull();
        add(contenido);
        //setSizeFull();
    }
}

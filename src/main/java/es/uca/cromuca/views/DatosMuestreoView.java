package es.uca.cromuca.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.forms.ClasificacionForm;
import es.uca.cromuca.forms.DatosMuestreoForm;
import es.uca.cromuca.services.*;
import es.uca.cromuca.services.enums.MetodoCapturaService;
import es.uca.cromuca.services.enums.PaisService;
import es.uca.cromuca.services.enums.TipoSustratoService;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "DatosMuestreoView", layout = MainView.class)
public class DatosMuestreoView extends VerticalLayout {
    private DatosMuestreoForm datosMuestreoForm;
    private H1 titulo = new H1("Datos de Muestreo");
    private ClasificacionForm clasificacionForm;

    @Autowired
    DatosMuestreoView(GeneroService generoService, CategoriaTaxonomicaService categoriaTaxonomicaService, FamiliaService familiaService, PhylumService phylumService,
                      EspecieService especieService, DatosMuestreoService datosMuestreoService, TipoSustratoService tipoSustratoService, PaisService paisService,
                      MetodoCapturaService metodoCapturaService) {
        this.clasificacionForm = new ClasificacionForm(generoService, especieService, familiaService, categoriaTaxonomicaService, phylumService, false);
        this.datosMuestreoForm = new DatosMuestreoForm(this, especieService, datosMuestreoService, tipoSustratoService, paisService,
                metodoCapturaService);
        this.clasificacionForm.datosMuestreoForm = datosMuestreoForm;
        VerticalLayout contenido = new VerticalLayout(clasificacionForm, datosMuestreoForm);
        //contenido.setSizeFull();
        add(contenido);
        //setSizeFull();
    }
}
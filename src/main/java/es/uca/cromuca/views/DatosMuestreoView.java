package es.uca.cromuca.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.forms.DatosMuestreoForm;
import es.uca.cromuca.services.DatosMuestreoService;
import es.uca.cromuca.services.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Secured("Admin")
@Route(value = "DatosMuestreoView", layout = MainView.class)
public class DatosMuestreoView extends AbstractView {
    private DatosMuestreoForm datosMuestreoForm;
    private H1 titulo = new H1("Datos de Muestreo");

    @Autowired
    DatosMuestreoView(EspecieService especieService, DatosMuestreoService datosMuestreoService) {
        this.datosMuestreoForm = new DatosMuestreoForm(this, especieService, datosMuestreoService);
        VerticalLayout contenido = new VerticalLayout(titulo, datosMuestreoForm);
        contenido.setSizeFull();
        add(contenido);
        setSizeFull();
    }
}
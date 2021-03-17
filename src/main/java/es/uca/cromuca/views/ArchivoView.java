package es.uca.cromuca.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.entities.Archivo;
import es.uca.cromuca.forms.ArchivoForm;
import es.uca.cromuca.services.ArchivoService;
import es.uca.cromuca.services.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Secured("Admin")
@Route(value = "ArchivoView", layout = MainView.class)
public class ArchivoView extends AbstractView {
    private Button comprobar = new Button("Buscar");
    private TextField numeroCatalogo = new TextField("Núm. Catálogo");
    private TextField numeroFrasco = new TextField();
    private Grid<Archivo> grid = new Grid<>(Archivo.class);
    private ArchivoService archivoService;
    private ArchivoForm form;

    @Autowired
    public ArchivoView(EspecieService especieService, ArchivoService archivoService) {
        this.form = new ArchivoForm(this, especieService, archivoService);
        this.archivoService = archivoService;

        Button addModeloBtn = new Button("Añade una archivo");
        addModeloBtn.addClickListener(e -> {
            grid.asSingleSelect().clear(); //clear para que borre si habia algo antes
            form.setArchivo(new Archivo()); //instancia un nuevo customer
        });

        grid.setColumns("formatoMultimedia", "autor", "fecha", "link", "observaciones");

        HorizontalLayout mainContent = new HorizontalLayout(grid, form); //metemos en un objeto el grid y formulario
        mainContent.setSizeFull();
        grid.setSizeFull();

        comprobar.addClickListener(e -> {
            if (especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue()) != null) {
                form.especieCreada = especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue());
                form.archivo = archivoService.findByEspecie(form.especieCreada);
                if (form.archivo != null) {
                    form.setArchivo(form.archivo);
                    Notification.show("Datos cargados", 3000, Notification.Position.MIDDLE);
                } else {
                    Notification.show("No hay datos de muestreo", 3000, Notification.Position.MIDDLE);
                    form.archivo = new Archivo();
                    form.setArchivo(form.archivo);
                }
                form.archivo.setEspecie(form.especieCreada);
            } else {
                Notification.show("Numero no existe", 3000, Notification.Position.MIDDLE);
                numeroCatalogo.setValue("");
                numeroFrasco.setValue("");
                form.archivo = null;
            }
        });

        HorizontalLayout numeroCatalogoLay = new HorizontalLayout(numeroCatalogo, numeroFrasco, comprobar);
        numeroCatalogoLay.setAlignItems(FlexComponent.Alignment.BASELINE);

        add(numeroCatalogoLay, addModeloBtn, mainContent);

        setSizeFull();

        updateList();

        form.setArchivo(null);

        grid.asSingleSelect().addValueChangeListener(event -> form.setArchivo(grid.asSingleSelect().getValue()));
    }

    public void updateList() {
        grid.setItems(archivoService.findAll());
    }
}

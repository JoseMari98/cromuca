package es.uca.cromuca.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.entities.Usuario;
import es.uca.cromuca.forms.UsuarioDatosForm;
import es.uca.cromuca.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "UsuarioView", layout = MainView.class)
public class UsuarioDatosView extends VerticalLayout{
    private UsuarioDatosForm usuarioDatosForm;
    private H1 titulo = new H1();

    @Autowired
    UsuarioDatosView(UsuarioService usuarioService){
        this.usuarioDatosForm = new UsuarioDatosForm(usuarioService);
        if(UI.getCurrent().getSession().getAttribute(Usuario.class) == null)
            titulo.add("Regístrate");
        else
            titulo.add("Modifica tus datos.");
        VerticalLayout contenido = new VerticalLayout(titulo, usuarioDatosForm);
        contenido.setSizeFull();
        add(contenido);
        setSizeFull();
    }
}

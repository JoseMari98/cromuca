package es.uca.cromuca.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.forms.OlvidarContrasenaForm;
import es.uca.cromuca.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "OlvidarContrasena", layout = MainView.class)
public class OlvidarContrasenaView extends VerticalLayout{
    private OlvidarContrasenaForm olvidarContrasenaForm;
    private H1 titulo = new H1();

    @Autowired
    OlvidarContrasenaView(UsuarioService usuarioService){
        this.olvidarContrasenaForm = new OlvidarContrasenaForm(usuarioService);
        titulo.add("Inserta una nueva contraseña");
        VerticalLayout contenido = new VerticalLayout(titulo, olvidarContrasenaForm);
        contenido.setSizeFull();
        add(contenido);
        setSizeFull();
    }
}

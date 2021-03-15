package es.uca.cromuca.forms;

//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import es.uca.cromuca.entities.Usuario;
import es.uca.cromuca.services.UsuarioService;

public class UsuarioDatosForm extends FormLayout {
    private TextField username = new TextField("Nombre usuario");
    private PasswordField password = new PasswordField("Contraseña");
    private PasswordField confirmPassword = new PasswordField("Confirmar contraseña");
    private BeanValidationBinder<Usuario> binder = new BeanValidationBinder<>(Usuario.class);
    private UsuarioService usuarioService;
    private Button save = new Button("Continuar");
    private Usuario usuario;

    public UsuarioDatosForm(UsuarioService usuarioService) {
        if (UI.getCurrent().getSession().getAttribute(Usuario.class) != null) {
            usuario = usuarioService.listarPorUsername(UI.getCurrent().getSession().getAttribute(Usuario.class).getUsername());
            usuario.setPassword("");
            confirmPassword.setValue("");
            binder.setBean(usuario);
        } else
            usuario = new Usuario();
        this.usuarioService = usuarioService;

        password.setRequired(true);
        confirmPassword.setRequired(true);
        username.setRequired(true);
        username.addValueChangeListener(e -> {
            if (usuarioService.listarPorUsername(username.getValue()) != null && usuario == null) {
                username.clear();
                Notification.show("Usuario repetido", 3000, Notification.Position.MIDDLE);
            } else {
                if (usuarioService.listarPorUsername(username.getValue()) != null && usuario != null && !usuarioService.listarPorUsername(username.getValue()).getId().equals(usuario.getId())) {
                    username.clear();
                    Notification.show("Usuario repetido", 3000, Notification.Position.MIDDLE);
                }
            }
        });
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(username, password, confirmPassword, save);
        save.addClickShortcut(Key.ENTER);

        binder.bindInstanceFields(this);


        Dialog dialog = new Dialog();
        Label label = new Label("¿Están correctos tus datos?");

        Button confirmButton = new Button("Confirmar", event -> {
            try {
                save();
            } catch (Exception e) {
                e.printStackTrace();
            }
            dialog.close();
        });
        confirmButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        Button cancelButton = new Button("Cancelar", event -> dialog.close());
        dialog.add(label, confirmButton, cancelButton);
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        save.addClickListener(event -> dialog.open());
        confirmButton.addClickShortcut(Key.ENTER);
    }

    public void save() throws Exception {
        if (binder.validate().isOk()) {
            if (password.getValue().equals(confirmPassword.getValue())) {
                usuario.setRole("Admin");
                usuario.setUsername(username.getValue());
                usuario.setPassword(password.getValue());
                usuarioService.create(usuario);
                if (UI.getCurrent().getSession().getAttribute(Usuario.class) != null) {
                    Notification.show("Modificado con éxito", 3000, Notification.Position.MIDDLE);
                    UI.getCurrent().getSession().setAttribute(Usuario.class, usuario);
                    UI.getCurrent().navigate("");
                } else {
                    Notification.show("Registrado con éxito", 3000, Notification.Position.MIDDLE);
                    UI.getCurrent().navigate("Login");
                }
            } else
                Notification.show("Las constraseñas no coinciden", 5000, Notification.Position.MIDDLE);
        } else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }
}
package es.uca.cromuca.forms;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.validator.EmailValidator;
import es.uca.cromuca.entities.Usuario;
import es.uca.cromuca.services.UsuarioService;

public class OlvidarContrasenaForm extends FormLayout {
    private EmailField email = new EmailField("Email");
    private PasswordField password = new PasswordField("Contraseña");
    private PasswordField confirmPassword = new PasswordField("Confirmar contraseña");
    private BeanValidationBinder<Usuario> binder = new BeanValidationBinder<>(Usuario.class);
    private UsuarioService usuarioService;
    private Button save = new Button("Continuar");
    private Usuario usuario;

    public OlvidarContrasenaForm(UsuarioService usuarioService) {
        /*if (UI.getCurrent().getSession().getAttribute(Usuario.class) != null) {
            usuario = usuarioService.listarPorUsername(UI.getCurrent().getSession().getAttribute(Usuario.class).getUsername());
            usuario.setPassword("");
            confirmPassword.setValue("");
            //binder.setBean(usuario);
        } else*/
        this.usuarioService = usuarioService;

        email.setRequiredIndicatorVisible(true);
        password.setRequired(true);
        confirmPassword.setRequired(true);

        email.addValueChangeListener(e -> {
            if(usuarioService.listarPorEmail(email.getValue()) == null) {
                Notification.show("Email no existe", 3000, Notification.Position.MIDDLE);
            }
        });
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(email, password, confirmPassword, save);
        save.addClickShortcut(Key.ENTER);

        binder.bindInstanceFields(this);

        Dialog dialog = new Dialog();
        Label label = new Label("¿Están correctos tus datos?");

        Button confirmButton = new Button("Confirmar", event -> {
            try {
                save();
            } catch (MessagingException e) {
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

    public void save() throws MessagingException {
        binder.forField(email)
                // Explicit validator instance
                .withValidator(new EmailValidator(
                        "No es un formato válido. Ejemplo de formato válido: usuario@gmail.com"))
                .bind(Usuario::getEmail, Usuario::setEmail);
        if(binder.validate().isOk()) {
            if(password.getValue().equals(confirmPassword.getValue())){
                usuario = usuarioService.listarPorEmail(email.getValue());
                usuario.setRole("User");
                usuario.setPassword(password.getValue());
                usuarioService.create(usuario);
                Notification.show("Contraseña actualizada", 3000, Notification.Position.MIDDLE);
                UI.getCurrent().getSession().setAttribute(Usuario.class, usuario);
                UI.getCurrent().navigate("");
            } else
                Notification.show("Las constraseñas no coinciden", 5000, Notification.Position.MIDDLE);
        }
        else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }
}

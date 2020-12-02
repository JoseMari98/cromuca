package es.uca.cromuca.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.entities.Usuario;
import es.uca.cromuca.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;


@Route(value = "Login", layout = MainView.class)
public class LoginView extends VerticalLayout {

    UsuarioService service;
    AuthenticationManager authenticationManager;
    LoginOverlay loginForm;

    @Autowired
    public LoginView(UsuarioService service, AuthenticationManager authenticationManager) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        loginForm = new LoginOverlay();
        loginForm.setI18n(createEspanol());
        loginForm.setOpened(true);
        loginForm.addForgotPasswordListener(e -> {
            loginForm.close();
            UI.getCurrent().navigate("OlvidarContrasena");
            //UI.getCurrent().getPage().reload();
        });
        loginForm.setForgotPasswordButtonVisible(true);
        loginForm.addLoginListener(e -> signIn(e));
        add(loginForm);
    }

    private void signIn(AbstractLogin.LoginEvent e) {
        if (authenticate(e.getUsername(), e.getPassword())) {
            loginForm.close();
            UI.getCurrent().getSession().setAttribute(Usuario.class, service.loadUserByUsername(e.getUsername()));
            UI.getCurrent().navigate("");
            UI.getCurrent().getPage().reload();
        } else {
            loginForm.setError(true);
        }
    }

    public boolean authenticate(String username, String password) {
        try {
            Authentication token = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(token);
            return true;
        } catch (AuthenticationException ex) {
            return false;
        }
    }

    private LoginI18n createEspanol() {
        final LoginI18n i18n = LoginI18n.createDefault();

        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("CromUCA");
        i18n.getForm().setUsername("Usuario");
        i18n.getForm().setTitle("Inicio de sesión");
        i18n.getForm().setSubmit("Entrar");
        i18n.getForm().setPassword("Contraseña");
        i18n.getForm().setForgotPassword("¿Olvidó su contraseña?");
        i18n.getErrorMessage().setTitle("Usuario/contraseña inválidos");
        i18n.getErrorMessage()
                .setMessage("Introduzca su usuario y contraseña e inténtelo de nuevo");

        return i18n;
    }
}
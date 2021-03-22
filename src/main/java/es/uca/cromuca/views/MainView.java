package es.uca.cromuca.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import es.uca.cromuca.entities.Usuario;
import es.uca.cromuca.springclasses.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The main view contains a button and a click listener.
 */
@Theme(Lumo.class)
@PWA(name = "CromucaApp",
        shortName = "Cromuca",
        description = "Registra muestras",
        enableInstallPrompt = true)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends AppLayout {
    Tabs tabs = new Tabs();
    Span appName = new Span("Cromuca");
    final HorizontalLayout menuLayout = new HorizontalLayout();
    final boolean touchOptimized = true;
    Button logout = new Button(new Icon(VaadinIcon.SIGN_OUT));

    @Autowired
    public MainView() throws InterruptedException {
        logout.addClickListener(e -> signOut());
        logout.addThemeVariants(ButtonVariant.LUMO_ERROR);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.add(createTab(VaadinIcon.HOME, "Inicio", InicioView.class));

        if (!SecurityUtils.isUserLoggedIn()) {
            tabs.add(createTab(VaadinIcon.SIGN_IN, "Iniciar sesión", LoginView.class));
        } else {
            //hola
        }
        tabs.add(createTab(VaadinIcon.RECORDS, "DatosMuestreo", DatosMuestreoView.class));
        tabs.add(createTab(VaadinIcon.RECORDS, "Ejemplares", EjemplaresView.class));
        tabs.add(createTab(VaadinIcon.RECORDS, "Conservacion", ConservacionView.class));
        tabs.add(createTab(VaadinIcon.RECORDS, "Historial determinacion", HistorialDeterminacionView.class));
        tabs.add(createTab(VaadinIcon.RECORDS, "Ubicacion", UbicacionView.class));
        tabs.add(createTab(VaadinIcon.RECORDS, "Archivos multimedia", ArchivoView.class));
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        tabs.setFlexGrowForEnclosedTabs(1.0);
        addToNavbar(appName, tabs); //anadirlo a la barra vertical
        if (SecurityUtils.isUserLoggedIn()) {
            logout.getStyle().set("center", "auto");
            addToNavbar(logout);
        }
    }

    public static Tab createTab(Component content) {
        final Tab tab = new Tab();
        tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        tab.add(content);
        return tab;
    }

    public static Tab createTab(VaadinIcon icon, String title, Class<? extends Component> viewClass) {
        return createTab(populateLink(new RouterLink(null, viewClass), icon, title));
    }

    public static <T extends HasComponents> T populateLink(T a, VaadinIcon icon, String title) {
        a.add(icon.create());
        a.add(title);
        return a;
    }

    private void signOut() {
        UI.getCurrent().getPage().executeJavaScript("location.assign('logout')");
        UI.getCurrent().getSession().close();
        UI.getCurrent().getSession().setAttribute(Usuario.class, null);
        UI.getCurrent().setPollInterval(3000);
    }
}
package es.uca.cromuca.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
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
    Span appName = new Span("");
    final HorizontalLayout menuLayout = new HorizontalLayout();
    final boolean touchOptimized = true;
    Button logout = new Button(new Icon(VaadinIcon.SIGN_OUT));

    @Autowired
    public MainView() throws InterruptedException {
        appName.setWidth("400px");
        addToNavbar(appName);
        /*logout.addClickListener(e -> signOut());
        logout.addThemeVariants(ButtonVariant.LUMO_ERROR);*/
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.add(createTab(VaadinIcon.HOME, "Inicio", InicioView.class));

        /*if (!SecurityUtils.isUserLoggedIn()) {
        } else {
            //hola
        }*/
        tabs.add(createTab(VaadinIcon.RECORDS, "DatosMuestreo", DatosMuestreoView.class));
        tabs.add(createTab(VaadinIcon.RECORDS, "Ejemplares", EjemplaresView.class));
        tabs.add(createTab(VaadinIcon.RECORDS, "Conservacion", ConservacionView.class));
        tabs.add(createTab(VaadinIcon.RECORDS, "Historial determinacion", HistorialDeterminacionView.class));
        tabs.add(createTab(VaadinIcon.RECORDS, "Ubicacion", UbicacionView.class));
        tabs.add(createTab(VaadinIcon.RECORDS, "Archivos multimedia", ArchivoView.class));
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        tabs.setFlexGrowForEnclosedTabs(1.0);

        if (SecurityUtils.isUserLoggedIn()) {
            /*logout.getStyle().set("center", "auto");
            addToNavbar(logout);*/
            final String contextPath = VaadinServlet.getCurrent().getServletContext().getContextPath();
            final Tab logoutTab = createTab(createLogoutLink(contextPath));
            tabs.add(logoutTab);
        }
        addToNavbar(tabs); //anadirlo a la barra vertical
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

    /*private void signOut() {
        UI.getCurrent().getPage().executeJavaScript("location.assign('logout')");
        UI.getCurrent().getSession().close();
        UI.getCurrent().getSession().setAttribute(Usuario.class, null);
        UI.getCurrent().setPollInterval(3000);
    }*/

    private static Anchor createLogoutLink(String contextPath) {
        final Anchor a = populateLink(new Anchor(), VaadinIcon.ARROW_RIGHT, "Salir");
        a.setHref(contextPath + "/logout");
        return a;
    }
}
package es.uca.cromuca.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import es.uca.cromuca.entities.Usuario;
import es.uca.cromuca.services.*;
import es.uca.cromuca.springclasses.SecurityUtils;
import es.uca.cromuca.views.Gestion.*;
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
    final DrawerToggle drawerToggle = new DrawerToggle();
    final VerticalLayout menuLayout = new VerticalLayout();
    final boolean touchOptimized = true;
    Button logout = new Button(new Icon(VaadinIcon.SIGN_OUT));

    @Autowired
    public MainView(CategoriaTaxonomicaService categoriaTaxonomicaService, GeneroService generoService, EspecieService especieService, FamiliaService familiaService, PhylumService phylumService) throws InterruptedException {
        logout.addClickListener(e -> signOut());
        logout.addThemeVariants(ButtonVariant.LUMO_ERROR);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.add(createTab(VaadinIcon.HOME, "Inicio", InicioView.class));

        if (!SecurityUtils.isUserLoggedIn()) {
            tabs.add(createTab(VaadinIcon.SIGN_IN, "Iniciar sesión", LoginView.class));
        } else {
            //tabs.add(createTab(VaadinIcon.PLUS, "Crear receta", CrearRecetaView.class));
            if (SecurityUtils.hasRole("Admin")) {
                tabs.add(createTab(VaadinIcon.RECORDS, "Gestión Phylum", PhylumGestionView.class));
                tabs.add(createTab(VaadinIcon.RECORDS, "Gestión Categoria", CategoriaTaxonomicaGestionView.class));
                tabs.add(createTab(VaadinIcon.RECORDS, "Gestión Familia", FamiliaGestionView.class));
                tabs.add(createTab(VaadinIcon.RECORDS, "Gestión Genero", GeneroGestionView.class));
                tabs.add(createTab(VaadinIcon.RECORDS, "Gestión Pais", PaisGestionView.class));
                tabs.add(createTab(VaadinIcon.RECORDS, "Gestión Metodo Captura", MetodoCapturaGestionView.class));
                tabs.add(createTab(VaadinIcon.RECORDS, "Gestión Tipo sustrato", TipoSustratoGestionView.class));
                tabs.add(createTab(VaadinIcon.RECORDS, "Clasificacion", ClasificacionView.class));
                tabs.add(createTab(VaadinIcon.RECORDS, "DatosMuestreo", DatosMuestreoView.class));
                tabs.add(createTab(VaadinIcon.RECORDS, "Ejemplares", EjemplaresView.class));
                tabs.add(createTab(VaadinIcon.RECORDS, "Conservacion", ConservacionView.class));
            }
        }
        addToDrawer(menuLayout, tabs); //anadirlo al desplegable
        addToNavbar(touchOptimized, drawerToggle/*, createTab(VaadinIcon.HOME, "Inicio", InicioView.class)*/); //anadirlo a la barra vertical
        if (SecurityUtils.isUserLoggedIn()) {
            logout.getStyle().set("center", "auto");
            addToDrawer(logout);
        } else
            drawerToggle.clickInClient();
        //UI.getCurrent().navigate("Inicio");
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
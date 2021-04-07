package es.uca.cromuca.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import es.uca.cromuca.entities.Archivo;
import es.uca.cromuca.entities.DatosMuestreo;
import es.uca.cromuca.entities.Especie;
import es.uca.cromuca.entities.Prestamo;
import es.uca.cromuca.forms.ClasificacionForm;
import es.uca.cromuca.services.*;
import es.uca.cromuca.springclasses.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.textfieldformatter.CustomStringBlockFormatter;
import org.vaadin.textfieldformatter.CustomStringBlockFormatter.Builder;
import org.vaadin.textfieldformatter.CustomStringBlockFormatter.Options;

@Route(value = "")
public class InicioView extends VerticalLayout {
    //private Button configuracion = new Button("Ir");
    //private Button listaComida = new Button("Ir");
    //private Button listaCompra = new Button("Ir");
    private ClasificacionForm clasificacionForm;

    //private Image imagen = new Image("https://www.tododisca.com/wp-content/uploads/2019/04/dia-mundial-de-la-salud-1000x600.jpg", "fondo");

    @Autowired
    public InicioView(EspecieService especieService, GeneroService generoService, CategoriaTaxonomicaService categoriaTaxonomicaService,
                      FamiliaService familiaService, PhylumService phylumService, DatosMuestreoService datosMuestreoService,
                      EjemplaresService ejemplaresService, ConservacionService conservacionService, HistorialDeterminacionService historialDeterminacionService,
                      UbicacionService ubicacionService, ArchivoService archivoService, PrestamoService prestamoService) {
        this.clasificacionForm = new ClasificacionForm(generoService, especieService, familiaService, categoriaTaxonomicaService, phylumService, true);
        H1 titulo = new H1("");
        Image image = new Image("images/cangrejo.png", "cangrejo");
        image.setWidth("107.73");
        image.setHeight("332.25");
        VerticalLayout imagenLay = new VerticalLayout(image);
        HorizontalLayout contenido = new HorizontalLayout();
        contenido.add(imagenLay);

        if (SecurityUtils.isUserLoggedIn()) {

            Button nuevoRegistro = new Button("Nuevo registro");
            Button duplicarRegistro = new Button("Duplicar registro");
            Button editarRegistro = new Button("Editar Registro");
            Button buscarRegistro = new Button("Buscar");
            Button eliminarRegistro = new Button("Eliminar registro");
            Button exportarPDF = new Button("Exportar PDF");
            Button exportarCSV = new Button("Exportar CSV");

            Dialog dialog = new Dialog();
            TextField numeroCatalogo = new TextField("Núm. catálogo");
            TextField numeroFrasco = new TextField("");
            dialog.setCloseOnEsc(false);
            dialog.setCloseOnOutsideClick(false);

            Button confirmButtonEditar = new Button("Confirmar", event -> {
                String[] parts = numeroCatalogo.getValue().split("-");
                String part1 = parts[0]; // 004
                String part2 = parts[1];
                numeroCatalogo.setValue(part2);
                if (especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue()) != null) {
                    UI.getCurrent().getSession().setAttribute(Especie.class, especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue()));
                    UI.getCurrent().navigate("DatosMuestreoView");
                    dialog.close();
                } else {
                    numeroCatalogo.setValue("");
                    numeroFrasco.setValue("");
                    dialog.close();
                    Notification.show("No existe ese número", 3000, Notification.Position.MIDDLE);
                }

            });
            Button cancelButton = new Button("Cancelar", event -> {
                numeroCatalogo.setValue("");
                numeroFrasco.setValue("");
                dialog.close();
            });

            Button confirmButtonDuplicar = new Button("Confirmar", event -> {
                String[] parts = numeroCatalogo.getValue().split("-");
                String part1 = parts[0]; // 004
                String part2 = parts[1];
                numeroCatalogo.setValue(part2);
                if (especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue()) != null) {
                    Especie antiguo = especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue());
                    //clasificacionForm.especieCreada = especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue());
                    Especie duplicado = new Especie();
                    String frascoString = especieService.nuevoFrasco(numeroCatalogo.getValue());
                    Integer numero = Integer.parseInt(frascoString) + 1;
                    switch (numero.toString().length()) {
                        case 1:
                            duplicado.setNumeroFrasco("0" + numero.toString());
                            break;
                        case 2:
                            duplicado.setNumeroFrasco(numero.toString());
                            break;
                    }
                    duplicado.setGenero(antiguo.getGenero());
                    duplicado.setNumeroCatalogo(numeroCatalogo.getValue());
                    duplicado.setEspecie(antiguo.getEspecie());
                    duplicado.setFechaAlta(antiguo.getFechaAlta());
                    duplicado.setAutorAno(antiguo.getAutorAno());
                    duplicado.setPhylum(antiguo.getPhylum());
                    duplicado.setCategoriaTaxonomicaPpal(antiguo.getCategoriaTaxonomicaPpal());
                    duplicado.setFamilia(antiguo.getFamilia());
                    especieService.guardar(duplicado);
                    numeroFrasco.setValue(duplicado.getNumeroFrasco());
                    DatosMuestreo datosMuestreo = datosMuestreoService.findByEspecie(antiguo);
                    datosMuestreo.setId(null);
                    datosMuestreo.setEspecie(duplicado);
                    datosMuestreoService.guardar(datosMuestreo);
                    UI.getCurrent().getSession().setAttribute(Especie.class, duplicado);
                    Notification.show("Duplicado", 3000, Notification.Position.MIDDLE);
                    clasificacionForm.especieCreada = duplicado;
                    UI.getCurrent().navigate("DatosMuestreoView");
                    dialog.close();
                } else {
                    numeroCatalogo.setValue("");
                    numeroFrasco.setValue("");
                    dialog.close();
                    Notification.show("No existe ese número", 3000, Notification.Position.MIDDLE);
                }
            });

            Button confirmButtonEliminar = new Button("Confirmar", event -> {
                String[] parts = numeroCatalogo.getValue().split("-");
                String part1 = parts[0]; // 004
                String part2 = parts[1];
                numeroCatalogo.setValue(part2);
                if (especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue()) != null) {
                    Especie especie = especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue());
                    if (especie.getDatosMuestreo() != null)
                        datosMuestreoService.borrar(especie.getDatosMuestreo());
                    if (especie.getEjemplares() != null)
                        ejemplaresService.borrar(especie.getEjemplares());
                    if (especie.getConservacion() != null)
                        conservacionService.borrar(especie.getConservacion());
                    if (especie.getHistorialDeterminacion() != null)
                        historialDeterminacionService.borrar(especie.getHistorialDeterminacion());
                    if (especie.getUbicacion() != null) {
                        if (prestamoService.findByUbicacion(especie.getUbicacion()) != null)
                            for (Prestamo prestamo : prestamoService.findByUbicacion(especie.getUbicacion()))
                                prestamoService.borrar(prestamo);
                        ubicacionService.borrar(especie.getUbicacion());
                    }
                    if (archivoService.findByEspecie(especie) != null)
                        for (Archivo archivo : archivoService.findByEspecie(especie))
                            archivoService.borrar(archivo);
                    especieService.borrar(especieService.findByNumCatalogoAndFrasco(numeroCatalogo.getValue(), numeroFrasco.getValue()));

                    Notification.show("Registro borrado", 3000, Notification.Position.MIDDLE);
                    numeroCatalogo.setValue("");
                    numeroFrasco.setValue("");
                    dialog.close();
                } else {
                    numeroCatalogo.setValue("");
                    numeroFrasco.setValue("");
                    dialog.close();
                    Notification.show("No existe ese número", 3000, Notification.Position.MIDDLE);
                }

            });

            nuevoRegistro.addClickListener(e -> UI.getCurrent().navigate("ClasificacionView"));
            editarRegistro.addClickListener(e -> {
                dialog.removeAll();
                dialog.add(numeroCatalogo, numeroFrasco);
                dialog.add(new Div(confirmButtonEditar, cancelButton));
                dialog.open();
            });
            duplicarRegistro.addClickListener(e -> {
                dialog.removeAll();
                dialog.add(numeroCatalogo, numeroFrasco);
                dialog.add(new Div(confirmButtonDuplicar, cancelButton));
                dialog.open();
            });
            eliminarRegistro.addClickListener(e -> {
                dialog.removeAll();
                dialog.add(numeroCatalogo, numeroFrasco);
                dialog.add(new Div(confirmButtonEliminar, cancelButton));
                dialog.open();
            });
            buscarRegistro.addClickListener(e -> UI.getCurrent().navigate("BuscarView"));
            exportarCSV.addClickListener(e -> UI.getCurrent().navigate(""));
            exportarPDF.addClickListener(e -> UI.getCurrent().navigate(""));
            VerticalLayout buttonsLay = new VerticalLayout(nuevoRegistro, duplicarRegistro, editarRegistro, eliminarRegistro, buscarRegistro, exportarCSV, exportarPDF);
            //VerticalLayout loginLay = new VerticalLayout(nuevoRegistro, login);
            buttonsLay.setAlignItems(Alignment.CENTER);
            //HorizontalLayout contenido = new HorizontalLayout(registrateLay, loginLay);
            //VerticalLayout main = new VerticalLayout(titulo, contenido/*, imagen*/);
            /*loginLay.setAlignItems(Alignment.CENTER);
            contenido.setAlignItems(Alignment.CENTER);
            main.setAlignItems(Alignment.CENTER);*/
            setAlignItems(Alignment.CENTER);
            new Builder()
                    .blocks(4)
                    .prefix("UCA", false, "-")
                    .numeric()
                    .build().extend(numeroCatalogo);

            Options options = new Options();
            options.setBlocks(2);
            new CustomStringBlockFormatter(options).extend(numeroFrasco);

            contenido.add(buttonsLay);
        } else {
            Button consultor = new Button("Consultor");
            Button admin = new Button("Admin");
            /*Label loginTexto = new Label("Inicia sesión");
            Label registroTexto = new Label("Consultor");*/

            admin.addClickListener(e -> UI.getCurrent().navigate("Login"));
            consultor.addClickListener(e -> UI.getCurrent().navigate("DatosMuestreoView"));
            VerticalLayout botones = new VerticalLayout(consultor, admin);
            botones.setAlignItems(Alignment.CENTER);
            contenido.setAlignItems(Alignment.CENTER);
            setAlignItems(Alignment.CENTER);
            admin.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            consultor.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            contenido.add(botones);
        }
        this.setAlignItems(Alignment.CENTER);
        add(titulo, contenido);
    }
}

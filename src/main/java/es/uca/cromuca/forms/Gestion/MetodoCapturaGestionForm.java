package es.uca.cromuca.forms.Gestion;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import es.uca.cromuca.entities.enums.MetodoCaptura;
import es.uca.cromuca.services.enums.MetodoCapturaService;
import es.uca.cromuca.views.Gestion.MetodoCapturaGestionView;

public class MetodoCapturaGestionForm extends FormLayout {
    private TextField metodoCaptura = new TextField("Metodo Captura");
    private Button save = new Button("Guardar");
    private Button delete = new Button("Borrar");
    private Button volver = new Button("Volver");
    private MetodoCapturaGestionView metodoCapturaGestionView;
    private Binder<MetodoCaptura> binder = new Binder<>(MetodoCaptura.class);
    private MetodoCapturaService metodoCapturaService;

    public MetodoCapturaGestionForm(MetodoCapturaGestionView metodoCapturaGestionView, MetodoCapturaService metodoCapturaService) {
        this.metodoCapturaService = metodoCapturaService;
        this.metodoCapturaGestionView = metodoCapturaGestionView;

        HorizontalLayout buttons = new HorizontalLayout(save, delete, volver);
        volver.addThemeVariants(ButtonVariant.LUMO_ERROR);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(metodoCaptura, buttons);
        metodoCaptura.setRequired(true);
        save.addClickShortcut(Key.ENTER);

        binder.bindInstanceFields(this);

        volver.addClickListener(e -> {
            UI.getCurrent().navigate("DatosMuestreoView");
        });

        save.addClickListener(event -> {
            if (binder.getBean() != null)
                save();
        });
        delete.addClickListener(event -> {
            if (binder.getBean() != null)
                delete();
        });
    }

    public void setMetodoCaptura(MetodoCaptura metodoCaptura) {
        binder.setBean(metodoCaptura);

        if (metodoCaptura == null) {
            setVisible(false);
        } else {
            setVisible(true);
            //this.modelo.focus();
        }
    }

    public void save() {
        MetodoCaptura metodoCaptura = binder.getBean();
        if (binder.validate().isOk() && !binder.getBean().getMetodoCaptura().equals("")) {
            metodoCapturaService.guardar(metodoCaptura);
            this.metodoCapturaGestionView.updateList();
            setMetodoCaptura(null);
        } else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }

    public void delete() {
        MetodoCaptura metodoCaptura = binder.getBean();
        if (binder.validate().isOk() && !binder.getBean().getMetodoCaptura().equals("")) {
            metodoCapturaService.borrar(metodoCaptura);
            this.metodoCapturaGestionView.updateList();
            setMetodoCaptura(null);
        } else
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);

    }
}

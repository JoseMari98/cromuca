package es.uca.cromuca.forms.Gestion;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import es.uca.cromuca.entities.enums.Pais;
import es.uca.cromuca.services.enums.PaisService;
import es.uca.cromuca.views.Gestion.PaisGestionView;

public class PaisGestionForm extends FormLayout {
    private TextField pais = new TextField("Pais");
    private Button save = new Button("Guardar");
    private Button delete = new Button("Borrar");
    private PaisGestionView paisGestionView;
    private Binder<Pais> binder = new Binder<>(Pais.class);
    private PaisService paisService;

    public PaisGestionForm(PaisGestionView paisGestionView, PaisService paisService) {
        this.paisService = paisService;
        this.paisGestionView = paisGestionView;

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(pais, buttons);
        pais.setRequired(true);
        save.addClickShortcut(Key.ENTER);

        binder.bindInstanceFields(this);

        save.addClickListener(event -> {
            if (binder.getBean() != null)
                save();
        });
        delete.addClickListener(event -> {
            if (binder.getBean() != null)
                delete();
        });
    }

    public void setPais(Pais pais) {
        binder.setBean(pais);

        if (pais == null) {
            setVisible(false);
        } else {
            setVisible(true);
            //this.modelo.focus();
        }
    }

    public void save() {
        Pais pais = binder.getBean();
        if (binder.validate().isOk() && !binder.getBean().getPais().equals("")) {
            paisService.guardar(pais);
            this.paisGestionView.updateList();
            setPais(null);
        } else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }

    public void delete() {
        Pais pais = binder.getBean();
        if (binder.validate().isOk() && !binder.getBean().getPais().equals("")) {
            paisService.borrar(pais);
            this.paisGestionView.updateList();
            setPais(null);
        } else
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);

    }
}

package es.uca.cromuca.forms.Gestion;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import es.uca.cromuca.entities.enums.TipoSustrato;
import es.uca.cromuca.services.enums.TipoSustratoService;
import es.uca.cromuca.views.Gestion.TipoSustratoGestionView;

public class TipoSustratoGestionForm extends FormLayout {
    private TextField tipoSustrato = new TextField("Tipo Sustrato");
    private Button save = new Button("Guardar");
    private Button delete = new Button("Borrar");
    private TipoSustratoGestionView tipoSustratoGestionView;
    private Binder<TipoSustrato> binder = new Binder<>(TipoSustrato.class);
    private TipoSustratoService tipoSustratoService;

    public TipoSustratoGestionForm(TipoSustratoGestionView tipoSustratoGestionView, TipoSustratoService tipoSustratoService) {
        this.tipoSustratoService = tipoSustratoService;
        this.tipoSustratoGestionView = tipoSustratoGestionView;

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(tipoSustrato, buttons);
        tipoSustrato.setRequired(true);
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

    public void setTipoSustrato(TipoSustrato tipoSustrato) {
        binder.setBean(tipoSustrato);

        if (tipoSustrato == null) {
            setVisible(false);
        } else {
            setVisible(true);
            //this.modelo.focus();
        }
    }

    public void save() {
        TipoSustrato tipoSustrato = binder.getBean();
        if (binder.validate().isOk() && !binder.getBean().getTipoSustrato().equals("")) {
            tipoSustratoService.guardar(tipoSustrato);
            this.tipoSustratoGestionView.updateList();
            setTipoSustrato(null);
        } else {
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);
        }
    }

    public void delete() {
        TipoSustrato tipoSustrato = binder.getBean();
        if (binder.validate().isOk() && !binder.getBean().getTipoSustrato().equals("")) {
            tipoSustratoService.borrar(tipoSustrato);
            this.tipoSustratoGestionView.updateList();
            setTipoSustrato(null);
        } else
            Notification.show("Rellene los campos", 5000, Notification.Position.MIDDLE);

    }
}

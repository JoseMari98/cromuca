package es.uca.cromuca.views.main;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import es.uca.cromuca.springclasses.SecurityUtils;

public class AbstractView extends VerticalLayout implements BeforeEnterObserver {
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        final boolean accessGranted = SecurityUtils.isAccessGranted(event.getNavigationTarget());

        if(!accessGranted) {
            if(SecurityUtils.isUserLoggedIn()) {
                event.rerouteTo(ErrorView.class);
            }
            else {
                event.rerouteTo(LoginView.class);
            }
        }
    }
}
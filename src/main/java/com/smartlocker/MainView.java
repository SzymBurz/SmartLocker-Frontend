package com.smartlocker;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;

import java.awt.*;

@Route("Home")
public class MainView extends VerticalLayout {
    //Book new
    Button bookNewBtn;
    //View reservations
    Button viewRsvBtn;


    public MainView() {
        this.bookNewBtn = new Button("Book new");
        this.viewRsvBtn = new Button("View Reservations");

        configureBtns();
    }

    private void configureBtns() {
        bookNewBtn.addClickListener(e -> {
            UI.getCurrent().getUI().ifPresent(b -> b.navigate(BookingView.class));
        });
        viewRsvBtn.addClickListener(e -> {
            UI.getCurrent().getUI().ifPresent(b -> b.navigate(BookingView.class));
        });
    }
}

package com.smartlocker;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import java.awt.*;

@Route("")
public class MainView extends VerticalLayout {
    //Book new
    Button bookNewBtn;
    //View reservations
    Button viewRsvBtn;

    H2 header;


    public MainView() {
        this.bookNewBtn = new Button("Book new");
        this.viewRsvBtn = new Button("View Reservations");
        this.header = new H2("Actions");


        VerticalLayout vl1 = new VerticalLayout(header, bookNewBtn, viewRsvBtn);
        VerticalLayout vl2 = new VerticalLayout(header);
        vl1.setAlignItems(Alignment.CENTER);
        vl1.setAlignItems(FlexComponent.Alignment.STRETCH);

        add(vl2, vl1);

        configureBtns();
    }

    private void configureBtns() {
        bookNewBtn.addClickListener(e -> {
            UI.getCurrent().getUI().ifPresent(b -> b.navigate(BookingView.class));
        });
        viewRsvBtn.addClickListener(e -> {
            UI.getCurrent().getUI().ifPresent(b -> b.navigate(ReservationsView.class));
        });
    }
}

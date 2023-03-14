package com.smartlocker;

import com.smartlocker.client.SmartLockerClient;
import com.smartlocker.domain.Reservation;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;

@Route("Reservations")
public class ReservationsView extends VerticalLayout {

    SmartLockerClient smartLockerClient;
    Grid<Reservation> reservationGrid;

    public ReservationsView(SmartLockerClient smartLockerClient) {
        this.smartLockerClient = smartLockerClient;
        this.reservationGrid = new Grid<>(Reservation.class, false);

        add(reservationGrid);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM HH:mm");
        configureGrid(formatter);
    }

    private void configureGrid(DateTimeFormatter formatter) {

        reservationGrid.addColumn(Reservation::getId).setHeader("Id").setComparator(Comparator.comparing(Reservation::getId));
        reservationGrid.addColumn(r -> r.getLocker().getId()).setHeader("Locker");
        reservationGrid.addColumn(r -> r.getStart().format(formatter)).setHeader("Start");
        reservationGrid.addColumn(r -> r.getEnd().format(formatter)).setHeader("End");
        reservationGrid.setItems(smartLockerClient.getReservationsForUser(smartLockerClient.getDemoUser()));
    }

}

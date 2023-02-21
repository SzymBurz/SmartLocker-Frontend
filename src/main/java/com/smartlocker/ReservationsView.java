package com.smartlocker;

import com.smartlocker.domain.Reservation;
import com.smartlocker.service.BookingService;
import com.smartlocker.service.LogingService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;

@Route("Reservations")
public class ReservationsView extends VerticalLayout {

    @Autowired
    LogingService logingService;
    @Autowired
    BookingService bookingService;
    Grid<Reservation> reservationGrid;

    public ReservationsView() {
        this.reservationGrid = new Grid<>(Reservation.class, false);

        add(reservationGrid);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM HH:mm");
        configureGrid(formatter);
    }

    private void configureGrid(DateTimeFormatter formatter) {

        reservationGrid.addColumn(Reservation::getReservationId).setHeader("Id").setComparator(Comparator.comparing(Reservation::getReservationId));
        reservationGrid.addColumn(r -> r.getLocker().getId()).setHeader("Locker");
        reservationGrid.addColumn(r -> r.getStart().format(formatter)).setHeader("Start");
        reservationGrid.addColumn(r -> r.getEnd().format(formatter)).setHeader("End");
        reservationGrid.setItems(bookingService.getReservationsForUser(logingService.generateUserForDemo()));
    }

}

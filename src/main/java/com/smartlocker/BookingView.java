package com.smartlocker;

import com.smartlocker.domain.LockerPeriod;
import com.smartlocker.domain.LockerReservations;
import com.smartlocker.repository.LockerRepo;
import com.smartlocker.repository.ReservationRepo;
import com.smartlocker.service.BookingService;
import com.smartlocker.service.LogingService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.smartlocker.domain.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Route("Booking")
public class BookingView extends VerticalLayout {

    private BookingService bookingService;
    private LogingService logingService;
    private H2 header1;
    private Grid<LockerReservations> periodGrid;
    private H2 header2;
    private DateTimePicker start;
    private DateTimePicker end;
    private ComboBox<Size> size;
    private Button book;
    private Dialog dialog;
    private List<LockerReservations> gridContent;


    public BookingView(BookingService bookingService) {
        this.bookingService = bookingService;
        this.logingService = LogingService.INSTANCE;
        this.header1 = new H2("Booking");
        this.periodGrid = new Grid<>(LockerReservations.class, false);
        this.header2 = new H2("Details");
        this.start = new DateTimePicker("Start");
        this.end = new DateTimePicker("End");
        this.size = new ComboBox<>("Size");
        this.book = new Button("Book");
        this.dialog = new Dialog();
        this.gridContent = new ArrayList<>();


        configureGrid();
        configureSizeBox();
        configureDateTimePickers();
        configureButton();
        configureDialog();

        add(header1, size, start, end, periodGrid, book);

    }

    private void configureDialog() {
        dialog.add("end of period can't be before start");

    }

    //TODO
    private void configureButton() {
        book.addClickListener(e -> bookingService.bookLocker(periodGrid.asSingleSelect().getValue().getLocker(), logingService.generateUserForDemo(), start.getValue(), end.getValue()));
    }

    private void configureDateTimePickers() {
        start.setValue(LocalDateTime.now());
        end.setValue(LocalDateTime.now().plusHours(3L));
        start.setMin(LocalDateTime.now());
        end.setMin(LocalDateTime.now().plusHours(1));
        start.addValueChangeListener(e -> refreshGrid());
        end.addValueChangeListener(e -> refreshGrid());
    }

    private void configureSizeBox() {

        size.setItems(Size.values());
        size.addValueChangeListener(e -> refreshGrid());
    }

    private void configureGrid() {
        periodGrid.setHeight("200px");
        periodGrid.addColumn(e -> e.getLocker().getId()).setHeader("Locker nr.");
        periodGrid.addColumn(e -> e.reservationsToString()).setHeader("Reservations");
        periodGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
    }

    private void refreshGrid() {
        if((!size.isEmpty() && !start.isEmpty() && !end.isEmpty()) && start.getValue().isAfter(end.getValue())) {
            dialog.setOpened(true);
        } else if ((!size.isEmpty() && !start.isEmpty() && !end.isEmpty()) && start.getValue().isBefore(end.getValue())) {
            gridContent.clear();
            gridContent.addAll(bookingService.availabilityCheckGeneral(size.getValue(), start.getValue(), end.getValue()));
            periodGrid.setItems(gridContent);
        }
    }
}

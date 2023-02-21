package com.example.smartlocker;

import com.smartlocker.domain.Locker;
import com.smartlocker.domain.LockerReservations;
import com.smartlocker.domain.Reservation;
import com.smartlocker.domain.Size;
import com.smartlocker.generator.ReservationGenerator;
import com.smartlocker.repository.LockerRepo;
import com.smartlocker.repository.ReservationRepo;
import com.smartlocker.service.BookingService;
import com.smartlocker.service.LogingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
@SpringBootTest
public class BookingServiceTestSuite {

    @Autowired
    LogingService logingService;

    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    ReservationGenerator reservationGenerator = new ReservationGenerator();
    @Autowired
    BookingService bookingService = new BookingService();


    @Test
    public void reservationsTest() {
        reservationRepo.getReservationsList().clear();
        reservationGenerator.generateExampleReservationData();
        for(Reservation r :reservationRepo.getReservationsList()){
            System.out.println(r);
        }
        Assertions.assertNotNull(reservationRepo.getReservationsList());
        reservationRepo.getReservationsList().clear();
    }


    @Test
    public void availabilityCheck() {

        reservationRepo.getReservationsList().clear();
        reservationGenerator.generateExampleReservationData();
        for(Reservation r :reservationRepo.getReservationsList()){
            System.out.println(r);
        }

        List<LockerReservations> lockerReservationsListS = bookingService.availabilityCheckGeneral(Size.S, LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3));
        System.out.println("  ");
        System.out.println("  ");
        System.out.println("  ");
        System.out.println("  ");
        System.out.println("  ");
        for (LockerReservations lrs : lockerReservationsListS) {
            System.out.println(lrs);
        }

        List<LockerReservations> lockerReservationsListM = bookingService.availabilityCheckGeneral(Size.M, LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3));
        System.out.println("  ");
        System.out.println("  ");
        System.out.println("  ");
        System.out.println("  ");
        System.out.println("  ");
        for (LockerReservations lrm : lockerReservationsListM) {
            System.out.println(lrm);
        }

        List<LockerReservations> lockerReservationsListL = bookingService.availabilityCheckGeneral(Size.L, LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3));
        System.out.println("  ");
        System.out.println("  ");
        System.out.println("  ");
        System.out.println("  ");
        System.out.println("  ");
        for (LockerReservations lrl : lockerReservationsListL) {
            System.out.println(lrl);
        }

        reservationRepo.getReservationsList().clear();
    }

    @Test
    public void reservationsForUser() {
        reservationRepo.getReservationsList().clear();
        reservationGenerator.generateExampleReservationData();
        List<Reservation> list = bookingService.getReservationsForUser(logingService.generateUserForDemo());
        System.out.println(list);
        reservationRepo.getReservationsList().clear();
    }




}

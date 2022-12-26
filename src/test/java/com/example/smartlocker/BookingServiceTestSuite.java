package com.example.smartlocker;

import com.smartlocker.domain.Locker;
import com.smartlocker.domain.LockerReservations;
import com.smartlocker.domain.Reservation;
import com.smartlocker.domain.Size;
import com.smartlocker.generator.ReservationGenerator;
import com.smartlocker.repository.LockerRepo;
import com.smartlocker.repository.ReservationRepo;
import com.smartlocker.service.BookingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class BookingServiceTestSuite {


    ReservationGenerator reservationGenerator = new ReservationGenerator();
    BookingService bookingService = new BookingService();


    @Test
    public void reservationsTest() {
        ReservationRepo.INSTANCE.getReservationsList().clear();
        reservationGenerator.generateExampleReservationData();
        for(Reservation r :ReservationRepo.INSTANCE.getReservationsList()){
            System.out.println(r);
        }
        Assertions.assertNotNull(ReservationRepo.INSTANCE.getReservationsList());
        ReservationRepo.INSTANCE.getReservationsList().clear();
    }

    /*
    @Test
    public void availabilityCheckForLockerTest() {

     ReservationRepo.INSTANCE.getReservationsList().clear();
        reservationGenerator.generateExampleReservationData();
        for(Locker l: LockerRepo.INSTANCE.isSize(Size.S)) {
            System.out.println(l);
            System.out.println(bookingService.availabilityCheckForLockerXX(l, start, end));

        }

     ReservationRepo.INSTANCE.getReservationsList().clear();
    }

     */

    @Test
    public void availabilityCheck() {

        ReservationRepo.INSTANCE.getReservationsList().clear();
        reservationGenerator.generateExampleReservationData();
        for(Reservation r :ReservationRepo.INSTANCE.getReservationsList()){
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

        ReservationRepo.INSTANCE.getReservationsList().clear();
    }




}

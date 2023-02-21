package com.example.smartlocker;

import com.smartlocker.domain.Reservation;
import com.smartlocker.domain.User;
import com.smartlocker.generator.ReservationGenerator;
import com.smartlocker.repository.LockerRepo;
import com.smartlocker.repository.ReservationRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class ReservationRepoAndGeneratorTest {

    @Autowired
    LockerRepo lockerRepo;

    ReservationGenerator reservationGenerator = new ReservationGenerator();

    @Test
    public void repositoryGeneratorIntegrationTest() {
        ReservationRepo.getInstance().clear();
        ReservationRepo.getInstance();

        reservationGenerator.generateExampleReservationData();
        for(Reservation r : ReservationRepo.getInstance().getReservationsList())
        System.out.println(r);

        Assertions.assertFalse(ReservationRepo.getInstance().getReservationsList().isEmpty());
        ReservationRepo.getInstance().clear();


    }

    @Test
    public void addingReservation() {
        ReservationRepo.getInstance().clear();
        User user = new User("Kasztan", "1");
        ReservationRepo.getInstance().add(
                new Reservation(
                        LocalDateTime.now(),
                        LocalDateTime.now().minusHours(1L),
                        user,
                        lockerRepo.getLockerById(1)
                )
        );
        Assertions.assertEquals(1, ReservationRepo.getInstance().getReservationsList().size());
        ReservationRepo.getInstance().clear();
    }
}

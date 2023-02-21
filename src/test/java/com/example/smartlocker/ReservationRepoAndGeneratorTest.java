package com.example.smartlocker;

import com.smartlocker.domain.Reservation;
import com.smartlocker.domain.User;
import com.smartlocker.generator.ReservationGenerator;
import com.smartlocker.repository.LockerRepo;
import com.smartlocker.repository.ReservationRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ReservationRepoAndGeneratorTest {

    @Autowired
    LockerRepo lockerRepo;
    @Autowired
    ReservationGenerator reservationGenerator;
    @Autowired
    ReservationRepo reservationRepo;

    @Test
    public void repositoryGeneratorIntegrationTest() {
        reservationRepo.clear();

        reservationGenerator.generateExampleReservationData();
        for(Reservation r : reservationRepo.getReservationsList())
        System.out.println(r);

        Assertions.assertFalse(reservationRepo.getReservationsList().isEmpty());
        reservationRepo.clear();


    }

    @Test
    public void addingReservation() {
        reservationRepo.clear();
        User user = new User("Kasztan", "1");
        reservationRepo.add(
                new Reservation(
                        LocalDateTime.now(),
                        LocalDateTime.now().minusHours(1L),
                        user,
                        lockerRepo.getLockerById(1)
                )
        );
        Assertions.assertEquals(1, reservationRepo.getReservationsList().size());
        reservationRepo.clear();
    }
}

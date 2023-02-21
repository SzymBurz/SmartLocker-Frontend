package com.smartlocker;

import com.smartlocker.generator.ReservationGenerator;
import com.smartlocker.repository.ReservationRepo;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@PWA(name = "SmartLocker Application",
        shortName = "SmaLoApp")
@SpringBootApplication
public class SmartLockerApplication {

    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    ReservationGenerator reservationGenerator;

    public static void main(String[] args) {
        SpringApplication.run(SmartLockerApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(){

        return (args) -> {
            reservationRepo.getReservationsList().clear();
            reservationGenerator.generateExampleReservationData();
        };
    }
}

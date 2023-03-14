package com.smartlocker;

import com.vaadin.flow.server.PWA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@PWA(name = "SmartLocker Application",
        shortName = "SmaLoApp")
@SpringBootApplication
public class SmartLockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartLockerApplication.class, args);
    }


}

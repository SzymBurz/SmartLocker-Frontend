package com.smartlocker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@Data
public class Reservation {
    LocalDateTime start;
    LocalDateTime end;
    User user;
    Locker locker;
    int reservationId;

    public Reservation(LocalDateTime start, LocalDateTime end, User user, Locker locker) {
        this.start = start;
        this.end = end;
        this.user = user;
        this.locker = locker;
    }

    public Reservation(int reservationId) {
        this.reservationId = reservationId;
    }
}

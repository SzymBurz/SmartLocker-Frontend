package com.smartlocker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {
    int id;
    LocalDateTime start;
    LocalDateTime end;
    User user;
    Locker locker;

    public Reservation(LocalDateTime start, LocalDateTime end, User user, Locker locker) {
        this.start = start;
        this.end = end;
        this.user = user;
        this.locker = locker;
    }
}

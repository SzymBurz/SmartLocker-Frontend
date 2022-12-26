package com.smartlocker.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class LockerPeriod extends Period{

    Locker locker;

    public LockerPeriod(LocalDateTime start, LocalDateTime end, Locker locker) {
        super(start, end);
        this.locker = locker;
    }
}

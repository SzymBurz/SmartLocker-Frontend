package com.smartlocker.domain;

import lombok.*;

@NoArgsConstructor
@Data
public class Locker {
    int id;
    Size size;

    public Locker(int id, Size size) {
        this.id = id;
        this.size = size;
    }
}

package com.smartlocker.domain;

import lombok.Data;
import com.smartlocker.domain.Size;

@Data
public class Locker {
    final int id;
    boolean open;
    Size size;

    public Locker(int id, Size size) {
        this.id = id;
        this.size = size;
    }


}

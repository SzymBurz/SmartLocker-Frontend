package com.smartlocker.service;

import com.smartlocker.domain.User;
import org.springframework.stereotype.Service;

public enum LogingService {
    INSTANCE;

    public User generateUserForDemo() {
        return new User("Kiemoon", "1");
    }
}

package com.smartlocker.service;

import com.smartlocker.domain.User;
import org.springframework.stereotype.Service;

public enum LogingService {
    INSTANCE;

    User demoUser = new User("Kiemoon", "1");

    public User generateUserForDemo() {
        return demoUser;
    }

    public static LogingService getInstance() {
        return INSTANCE;
    }
}

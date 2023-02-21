package com.smartlocker.service;

import com.smartlocker.domain.User;
import org.springframework.stereotype.Service;

@Service
public class LogingService {

    User demoUser = new User("Kiemoon", "1");

    public User generateUserForDemo() {
        return demoUser;
    }

}

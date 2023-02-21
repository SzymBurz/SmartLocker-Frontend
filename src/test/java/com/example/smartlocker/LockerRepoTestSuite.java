package com.example.smartlocker;


import com.smartlocker.repository.LockerRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class LockerRepoTestSuite {

    @Autowired
    LockerRepo lockerRepo;

    @Test
    void testGeneratingLockers() {

        lockerRepo.printOutLockers();

        Assertions.assertEquals(100, lockerRepo.getLockerListSize());
    }


}

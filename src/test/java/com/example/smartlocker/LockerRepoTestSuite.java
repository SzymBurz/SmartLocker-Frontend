package com.example.smartlocker;


import com.smartlocker.repository.LockerRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LockerRepoTestSuite {

    @Test
    void testGeneratingLockers() {
        LockerRepo lockerRepo = LockerRepo.getInstance();

        lockerRepo.printOutLockers();

        Assertions.assertEquals(100, lockerRepo.getLockerListSize());
    }


}

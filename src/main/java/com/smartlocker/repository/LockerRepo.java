package com.smartlocker.repository;

import com.smartlocker.domain.Locker;
import com.smartlocker.domain.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum LockerRepo {

    INSTANCE;

    private static List<Locker> lockerList = new ArrayList<>(generateExampleData());
    public void open(Locker locker, int userId) {
    }

    public boolean isSize(Size size, int lockerId) {
        boolean isSize = lockerList.get(lockerId).getSize() == size;
        return isSize;
    }

    public List<Locker> isSize(Size size) {
        List<Locker> output = new ArrayList<>();
        output.addAll(lockerList.stream().filter(e -> e.getSize() == size).collect(Collectors.toList()));
        return output;
    }

    public static LockerRepo getInstance() {
        return INSTANCE;
    }

    private static List<Locker> generateExampleData() {

        List<Locker> exampleList = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                if (i <= 30) {
                    exampleList.add(new Locker(i, Size.S));
                } else if (i > 30 && i <= 70) {
                    exampleList.add(new Locker(i, Size.M));
                } else {
                    exampleList.add(new Locker(i, Size.L));
                }
            }

        return exampleList;
    }

    public void printOutLockers(){
        for(Locker l: lockerList) {
            System.out.println(l);
        }
    }

    public int getLockerListSize() {
        return lockerList.size();
    }

    public Locker getLockerById(int lockerId) {
        return lockerList.get(lockerId);
    }

}

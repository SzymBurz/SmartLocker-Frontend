package com.smartlocker.repository;

import com.smartlocker.domain.*;
import com.smartlocker.service.LogingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class ReservationRepo {


    @Autowired
    LockerRepo lockerRepo;


    private List<Reservation> reservationsList = new ArrayList<>();
    public boolean add(Reservation reservation) {
        try {
            if(reservationsList.size() == 0) {
                reservation.setReservationId(0);
            } else {
                reservation.setReservationId(reservationsList.get(reservationsList.size() - 1).getReservationId() + 1);
            }
            reservationsList.add(reservation);
            return  true;
        } catch (Exception e) {
            return  false;
        }
    }

    public void changeReservation(int reservationId, LocalDateTime start, LocalDateTime end) {

        //TODO: add collision check
        for(Reservation r: reservationsList) {

            if (r.getReservationId() == reservationId){
                r.setStart(start);
                r.setEnd(end);
            }
        }
    }

    public List<Reservation> getReservationsList() {
        return reservationsList;
    }

    public void clear() {
        reservationsList.clear();
    }

}

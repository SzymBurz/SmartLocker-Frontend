package com.smartlocker.service;

import com.smartlocker.domain.*;
import com.smartlocker.repository.LockerRepo;
import com.smartlocker.repository.ReservationRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService {


    LockerRepo lockerRepo = LockerRepo.INSTANCE;

    ReservationRepo reservationRepo = ReservationRepo.INSTANCE;

    public boolean bookLocker(Locker locker, User user, LocalDateTime start, LocalDateTime end) {
        boolean output = true; //fixed for true for developing
        if(statusCheck(locker, start, end)) {
            output = reservationRepo.add(new Reservation(start, end, user, locker));
        }
        return output;
    }

    public void changeReservation(int reservationId, LocalDateTime start, LocalDateTime end) {
        reservationRepo.changeReservation(reservationId, start, end);
    }

    public void openLocker(Locker locker,int userId) {
        lockerRepo.open(locker, userId);
    }

    public boolean statusCheckNow(Locker locker) {
        List<Reservation> reservationsList = reservationRepo.getReservationsList();

        boolean isFree = false;

        for(Reservation r: reservationsList) {
            LocalDateTime now = LocalDateTime.now();
            if (r.getLocker() == locker && r.getStart().isBefore(now) && r.getEnd().isAfter(now)){
                isFree = false;
            } else {
                isFree = true;
            }
        }
        return isFree;

    }
    public boolean statusCheck(Locker locker, LocalDateTime start, LocalDateTime end) {
        List<Reservation> reservationsList = reservationRepo.getReservationsList();

        boolean isFree = false;
        for(Reservation r: reservationsList) {
            if (r.getLocker() == locker && (r.getStart().isBefore(start) || r.getEnd().isAfter(end))){
                isFree = false;
            } else {
                isFree = true;
            }
        }

        return isFree;

    }
    /*

    //public List<Period> availabilityCheckForLocker(Locker locker, LocalDateTime start, LocalDateTime end) {
    public List<Period> availabilityCheckForLocker(Locker locker) {

        List<Period> availabilityList = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();

        List<Reservation> reservationsList = reservationRepo.getReservationsList();


        List<Reservation> reservationsForLocker = reservationsList.stream()
                .filter(e -> e.getLocker() == locker)
                .collect(Collectors.toList());


        System.out.println(reservationsForLocker);
  /*
        //extracting reservations for given locker
        ListIterator<Reservation> listIterator =
                reservationsList.stream()
                        .filter(e -> e.getLocker() == locker)
                        .collect(Collectors.toList())
                        .listIterator(reservationsList.size());




        //creating periods of time when locker is free
        while (listIterator.hasPrevious()) {
            Reservation r = listIterator.previous();
            if(r.getStart().isAfter(now) && !listIterator.hasNext()) {
                availabilityList.add(new Period(r.getEnd(), r.getEnd().plusDays(1L)));
            } else if(r.getStart().isAfter(now) && r.getEnd().isAfter(now)) {
                availabilityList.add(new Period(r.getEnd(), listIterator.next().getStart()));
            } else if (r.getStart().isBefore(now) && r.getEnd().isAfter(now)) {
                availabilityList.add(new Period(r.getEnd(), listIterator.next().getStart()));
            } else if (r.getEnd().isBefore(now) && listIterator.next().getStart().isAfter(now)) {
                availabilityList.add(new Period(now, listIterator.next().getStart()));
            }

        }


        return availabilityList;
    }
*/
    public Optional<LockerReservations> availabilityCheckForLockerTime(Locker locker, LocalDateTime start, LocalDateTime end) {

        //Cała metoda działa dla specyficznej szafki i w specyficznym czasie
        Optional<LockerReservations> output = Optional.empty();

        //Reserwacje dla lockera l
        List<Reservation> reservationsForLocker = reservationRepo.getReservationsList().stream()
                .filter(e -> e.getLocker() == locker)
                .collect(Collectors.toList());

        System.out.println("Reservations for locker:" + locker.getId() + " " + locker.getSize());
        System.out.println(reservationsForLocker);

        //Jeśli rezerwacje dla locker l nie są puste to:
        if(!reservationsForLocker.isEmpty()) {

            boolean rtrn = true;
            List<Reservation> list = new ArrayList<>();

            //sprawdzamy czy kolidują z okresem czasu
            for (Reservation r : reservationsForLocker) {

                //sprwdź czy jest kolizja
                if (
                        !(r.getStart().isAfter(start) && r.getStart().isBefore(end))
                        && !(r.getEnd().isAfter(start) && r.getEnd().isBefore(end))
                        && !(r.getStart().equals(start))
                        && !(r.getEnd().equals(end))

                        /*
                        !((r.getStart().isBefore(start) || r.getStart().equals(start)) && (r.getEnd().isAfter(start) || r.getEnd().equals(end)))
                && !(r.getStart().isAfter(start) && r.getEnd().isBefore(end))
                && !(r.getEnd().isAfter(start) && r.getEnd().isBefore(end))
                && !(r.getStart().isAfter(start) && r.getEnd().isBefore(end))
                         */

                ) {

                    list.add(r);

                //jeśli jest
                } else {
                    rtrn = false;
                }

                //dodaj do outputu:
                if(!list.isEmpty() && rtrn) {
                    output = Optional.of(new LockerReservations(list));
                }
            }
        //jeśli są puste
        } else {
            output = Optional.of(new LockerReservations(locker));
        }

        return  output;
    }

    public List<LockerReservations> availabilityCheckGeneral(Size size, LocalDateTime start, LocalDateTime end) {

        //Do tego dodajemy i to będziemy zwracać
        List<LockerReservations> lockerReservationsList = new ArrayList<>();

       //Lista lockerów w rozmiarze size
        List<Locker> lockerList = lockerRepo.isSize(size);

        //zwraca zestaw reserwacji dla lockera. Zestaw może być pusty
        for (Locker l: lockerList) {

                //zwraca LockerReservations
                Optional<LockerReservations> lr = availabilityCheckForLockerTime(l, start, end);

                if(lr.isPresent()) {
                    lockerReservationsList.add(lr.get());
                }

        }

        return lockerReservationsList;
    }

}
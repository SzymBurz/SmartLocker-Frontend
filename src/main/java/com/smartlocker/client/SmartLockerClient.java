package com.smartlocker.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.smartlocker.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;

@Controller
public class SmartLockerClient {

    @Autowired
    RestTemplate restTemplate;

    String url = "http://localhost:8080/v1/api/";

    public List<LockerReservations> getReservationsCheck(Size size, LocalDateTime start, LocalDateTime end) throws JsonProcessingException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> sizeTimeMap = new HashMap<>();
        sizeTimeMap.put("size", size.toString());
        sizeTimeMap.put("start", start.toString());
        sizeTimeMap.put("end", end.toString());
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(sizeTimeMap, httpHeaders);
        String str = restTemplate.postForObject(url + "reservationsCheck", httpEntity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, LockerReservations.class);
        List<LockerReservations> lockerReservationsList = objectMapper.readValue(str, type);
        return lockerReservationsList;
    }

    public User getDemoUser() {
        return restTemplate.getForObject(url + "demoUser", User.class);
    }

    public List<Reservation> getReservationsForUser(User user) {
        return List.of(restTemplate.postForObject(url + "reservationsForUser",user, Reservation[].class));
    }

    public boolean bookLocker(Locker locker, User user, LocalDateTime start, LocalDateTime end) {
        Reservation reservationToPost = new Reservation(start, end, user, locker);
        return Boolean.TRUE.equals(restTemplate.postForObject(url + "newReservation", reservationToPost, Boolean.class));
    }
}

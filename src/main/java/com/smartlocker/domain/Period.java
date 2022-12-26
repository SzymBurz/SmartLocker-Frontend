package com.smartlocker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class Period {
    LocalDateTime start;
    LocalDateTime end;


}

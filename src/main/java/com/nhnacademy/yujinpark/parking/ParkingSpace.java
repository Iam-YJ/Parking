package com.nhnacademy.yujinpark.parking;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ParkingSpace {
    private String code;
    private Car car;
    private LocalDateTime entryTime;

    public ParkingSpace(String code, Car car, LocalDateTime entryTime) {
        this.code = code;
        this.car = car;
        this.entryTime = entryTime;
    }

    public String getCode() {
        return code;
    }

    public Car getCar() {
        return car;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}

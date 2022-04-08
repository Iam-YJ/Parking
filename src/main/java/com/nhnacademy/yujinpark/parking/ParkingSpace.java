package com.nhnacademy.yujinpark.parking;

public class ParkingSpace {
    private String code;
    private Car car;

    public ParkingSpace(String code, Car car) {
        this.code = code;
        this.car = car;
    }

    public String getCode() {
        return code;
    }

    public Car getCar() {
        return car;
    }
}

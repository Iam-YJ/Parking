package com.nhnacademy.yujinpark.parking;

public class Car {
    private String numer;

    public Car() {
    }

    public Car(String numer) {
        this.numer = numer;
    }

    public String getNumber() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

}

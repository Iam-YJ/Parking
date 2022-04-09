package com.nhnacademy.yujinpark.parking.subject;

public class Car {
    private String numer;
    private CarSize carSize;

    public Car(String numer) {
        this.numer = numer;
    }

    public Car(String numer, CarSize carSize) {
        this.numer = numer;
        this.carSize = carSize;
    }

    public String getNumber() {
        return numer;
    }

    public CarSize getCarSize() {
        return carSize;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

}

package com.nhnacademy.yujinpark.parking.subject;

public class User {
    private String userId;
    private Money money;
    private Car car;


    public User(String userId, Money money, Car car) {
        this.userId = userId;
        this.money = money;
        this.car = car;
    }

    public String getUserId() {
        return userId;
    }

    public Money getMoney() {
        return money;
    }

    public Car getCar() {
        return car;
    }
}

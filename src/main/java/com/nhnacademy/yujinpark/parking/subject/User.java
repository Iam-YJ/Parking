package com.nhnacademy.yujinpark.parking.subject;

import com.nhnacademy.yujinpark.parking.parkinglot.Coupon;
import com.nhnacademy.yujinpark.parking.payco.Payco;

public class User {
    private String userId;
    private Money money;
    private Car car;
    private Payco payco;
    private Coupon coupon;


    public User(String userId, Money money, Car car) {
        this.userId = userId;
        this.money = money;
        this.car = car;
    }

    public User(String userId, Money money, Car car, Payco payco) {
        this.userId = userId;
        this.money = money;
        this.car = car;
        this.payco = payco;
    }

    public User(String userId, Money money, Car car, Payco payco, Coupon coupon) {
        this.userId = userId;
        this.money = money;
        this.car = car;
        this.payco = payco;
        this.coupon = coupon;
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

    public Payco getPayco() {
        return payco;
    }

    public Coupon getCoupon() {
        return coupon;
    }
}

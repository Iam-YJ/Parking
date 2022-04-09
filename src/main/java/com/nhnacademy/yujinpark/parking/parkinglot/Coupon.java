package com.nhnacademy.yujinpark.parking.parkinglot;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Coupon {
    private int context;

    public Coupon(int context) {
        this.context = context;
    }

    public int getContext() {
        return context;
    }
}

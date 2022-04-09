package com.nhnacademy.yujinpark.parking.exception;

public class UserHaveNoCoupon extends IllegalArgumentException{
    public UserHaveNoCoupon(String s) {
        super(s);
    }
}

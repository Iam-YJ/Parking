package com.nhnacademy.yujinpark.parking.exception;

public class DoNotAllowCarExitException extends IllegalArgumentException{
    public DoNotAllowCarExitException(String s) {
        super(s);
    }
}

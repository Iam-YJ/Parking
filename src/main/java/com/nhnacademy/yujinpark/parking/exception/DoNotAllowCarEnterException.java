package com.nhnacademy.yujinpark.parking.exception;

public class DoNotAllowCarEnterException extends IllegalArgumentException {
    public DoNotAllowCarEnterException(String s) {
        super(s);
    }
}

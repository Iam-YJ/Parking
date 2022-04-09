package com.nhnacademy.yujinpark.parking.parkinglot;

import com.nhnacademy.yujinpark.parking.subject.User;
import java.math.BigDecimal;

interface ParkingSystem {

    String scanCarNumber();

    ParkingSpace enter(ParkingSpace parkingSpace);

    ParkingSpace exit(User user, ParkingSpace parkingSpace);

    BigDecimal calculateExitPay(ParkingSpace parkingSpace);

    void calculateParkingPayByUser(User user, BigDecimal exitPay);

    // SPEC 3
    BigDecimal changedCalculateExitPay(ParkingSpace parkingSpace);

    // SPEC 3
    boolean checkCarSize(ParkingSpace parkingSpace);

}

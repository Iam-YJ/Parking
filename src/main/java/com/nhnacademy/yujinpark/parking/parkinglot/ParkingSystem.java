package com.nhnacademy.yujinpark.parking.parkinglot;

import com.nhnacademy.yujinpark.parking.subject.User;
import java.math.BigDecimal;

interface ParkingSystem {

    String scanCarNumber();

    BigDecimal calculateExitPay(ParkingSpace parkingSpace);

    boolean calculateParkingPayByUser(User user, BigDecimal exitPay);

}

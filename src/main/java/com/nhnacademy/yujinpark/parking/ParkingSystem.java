package com.nhnacademy.yujinpark.parking;

import java.math.BigDecimal;

interface ParkingSystem {

    String scanCarNumber();

    BigDecimal calculateExitPay(ParkingSpace parkingSpace);

}

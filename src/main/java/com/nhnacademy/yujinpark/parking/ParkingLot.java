package com.nhnacademy.yujinpark.parking;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingLot implements ParkingSystem {
    List<ParkingSpace> spaces = new ArrayList<ParkingSpace>();


    public List<ParkingSpace> getSpaces() {
        return spaces;
    }

    public ParkingSpace enter(ParkingSpace parkingSpace) {
        spaces.add(parkingSpace);
        return parkingSpace;
    }

    public boolean checkCarInParkingLot(ParkingSpace parkingSpace) {
        for (ParkingSpace ps : spaces) {
            if (ps.getCar().equals(parkingSpace.getCar()) &&
                ps.getCode().equals(parkingSpace.getCode())) {
                return true;
            }
        }
        return false;
    }

    public ParkingSpace exit(ParkingSpace parkingSpace) {
        spaces.remove(parkingSpace);
        return parkingSpace;
    }

    @Override
    public String scanCarNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    @Override
    public BigDecimal calculateExitPay(ParkingSpace parkingSpace) {
        LocalDateTime now = LocalDateTime.now();
        long useTime = Duration.between(parkingSpace.getEntryTime(), now).toSeconds();

        return getExitPay(Math.abs(useTime));
    }

    public BigDecimal getExitPay(long useTime) {
        long payTime = useTime / 60;

        BigDecimal pay = new BigDecimal(0.0);

        if (payTime >= 1440) {
            long dayCount = (int)(payTime / 1440);
            payTime = payTime - (1440*dayCount);

            return pay.add(calculatePay(payTime)).add(calculateDayPay(dayCount));
        }

        if (payTime <= 30) {
            return pay.add(dailyMaximumPay(BigDecimal.valueOf(1000)));

        } else {
            payTime -= 30;
            return dailyMaximumPay(calculatePay(payTime).add(BigDecimal.valueOf(1000)));
        }
    }

    public BigDecimal dailyMaximumPay(BigDecimal inputPay) {
        if (inputPay.compareTo(BigDecimal.valueOf(10000)) == 1) {
            inputPay = BigDecimal.valueOf(10000);
        }
        return inputPay;
    }

    public BigDecimal calculatePay(long payTime){
        BigDecimal pay = new BigDecimal(0.0);

        payTime = (long) Math.ceil((payTime) / 10.0);
        return pay.add(dailyMaximumPay(BigDecimal.valueOf((500 * payTime))));
    }

    public BigDecimal calculateDayPay(long dayCount){
        return new BigDecimal(10000*dayCount);
    }
}

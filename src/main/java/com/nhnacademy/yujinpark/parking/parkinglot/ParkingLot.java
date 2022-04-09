package com.nhnacademy.yujinpark.parking.parkinglot;

import com.nhnacademy.yujinpark.parking.exception.DoNotAllowCarEnterException;
import com.nhnacademy.yujinpark.parking.exception.DoNotAllowCarExitException;
import com.nhnacademy.yujinpark.parking.exception.UserHaveNoCoupon;
import com.nhnacademy.yujinpark.parking.payco.Payco;
import com.nhnacademy.yujinpark.parking.payco.PaycoDiscountable;
import com.nhnacademy.yujinpark.parking.subject.CarSize;
import com.nhnacademy.yujinpark.parking.subject.User;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingLot implements ParkingSystem, PaycoDiscountable {
    List<ParkingSpace> spaces = new ArrayList<ParkingSpace>();

    public List<ParkingSpace> getSpaces() {
        return spaces;
    }

    @Override
    public ParkingSpace enter(ParkingSpace parkingSpace) {
        if(parkingSpace.getCar().getCarSize().equals(CarSize.LARGE)){
            throw new DoNotAllowCarEnterException("Large Size Car can't enter parkingLot");
        }
        spaces.add(parkingSpace);
        return parkingSpace;
    }

    @Override
    public boolean checkCarSize(ParkingSpace parkingSpace) {
        if(parkingSpace.getCar().getCarSize().equals(CarSize.LARGE)){
            return false;
        }
        return true;
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

    @Override
    public ParkingSpace exit(User user, ParkingSpace parkingSpace) {
        calculateParkingPayByUser(user, calculateExitPay(parkingSpace));
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

    @Override
    public void calculateParkingPayByUser(User user, BigDecimal exitPay) {
        if (exitPay.compareTo(user.getMoney().getAmount()) == 1) {
            throw new DoNotAllowCarExitException("money is not enough to exit parkingLot");
        }
    }

    // SPEC 3
    @Override
    public BigDecimal changedCalculateExitPay(ParkingSpace parkingSpace) {
        LocalDateTime now = LocalDateTime.now();
        long useTime = Duration.between(parkingSpace.getEntryTime(), now).toSeconds();

        if (parkingSpace.getCar().getCarSize().equals(CarSize.SMALL)) {
            return discountSmallCarExitPay(changedGetExitPay(Math.abs(useTime)));
        }

        return changedGetExitPay(Math.abs(useTime));
    }


    public BigDecimal getExitPay(long useTime) {
        long payTime = useTime / 60;

        BigDecimal pay = new BigDecimal(0.0);

        if (payTime >= 1440) {
            long dayCount = (int) (payTime / 1440);
            payTime = payTime - (1440 * dayCount);

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

    public BigDecimal calculatePay(long payTime) {
        BigDecimal pay = new BigDecimal(0.0);

        payTime = (long) Math.ceil((payTime) / 10.0);
        return pay.add(dailyMaximumPay(BigDecimal.valueOf((500 * payTime))));
    }

    public BigDecimal calculateDayPay(long dayCount) {
        return new BigDecimal(10000 * dayCount);
    }

    // SPEC 3
    public BigDecimal changedGetExitPay(long useTime) {
        long payTime = useTime / 60;

        BigDecimal pay = new BigDecimal(0.0);

        if (payTime >= 1440) {
            long dayCount = (int) (payTime / 1440);
            payTime = payTime - (1440 * dayCount);

            return pay.add(calculatePay(payTime)).add(changedCalculateDayPay(dayCount));
        }

        if (payTime <= 30) {
            return pay.add(changedDailyMaximumPay(BigDecimal.valueOf(0)));

        }

        if (payTime > 30 && payTime <= 60) {
            return pay.add(changedDailyMaximumPay(BigDecimal.valueOf(1000)));

        } else {
            payTime -= 60;
            return changedDailyMaximumPay(
                calculatePay(payTime).add(BigDecimal.valueOf(1000)));
        }
    }

    // SPEC 3
    public BigDecimal changedDailyMaximumPay(BigDecimal inputPay) {
        if (inputPay.compareTo(BigDecimal.valueOf(15000)) == 1) {
            inputPay = BigDecimal.valueOf(15000);
        }
        return inputPay;
    }


    // SPEC 3
    public BigDecimal changedCalculateDayPay(long dayCount) {
        return new BigDecimal(15000 * dayCount);
    }

    // SPEC 3
    public BigDecimal discountSmallCarExitPay(BigDecimal originalExitPay) {
        return new BigDecimal(String.valueOf(originalExitPay.divide(BigDecimal.valueOf(2))));
    }

    @Override
    public BigDecimal discount(User user, BigDecimal exitPay) {
        if(user.getPayco().equals(Payco.USER)){
            return exitPay.divide(BigDecimal.valueOf(10)).multiply(BigDecimal.valueOf(9));
        }
        return exitPay;
    }

//    @Override
//    public BigDecimal discountByCoupon(User user, ParkingSpace parkingSpace){
//        if(user.getCoupon().equals(null)){
//            throw new UserHaveNoCoupon("no coupon");
//        }
//
//       changedCalculateExitPay(parkingSpace.getEntryTime().plusHours(user.getCoupon().getContext()));
//    }
}

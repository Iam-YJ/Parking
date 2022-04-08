package com.nhnacademy.yujinpark.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingLot {
    List<ParkingSpace> spaces = new ArrayList<ParkingSpace>();


    public List<ParkingSpace> getSpaces() {
        return spaces;
    }

    public ParkingSpace enter(ParkingSpace parkingSpace) {
        spaces.add(parkingSpace);
        return parkingSpace;
    }

    public Car checkCarInParkingLot(){
       return null;
    }
}

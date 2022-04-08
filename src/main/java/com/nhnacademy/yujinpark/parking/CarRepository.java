package com.nhnacademy.yujinpark.parking;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarRepository {

    List<Car> carList = new ArrayList<Car>();

    Map<Car, LocalTime> carRepository = new HashMap<Car, LocalTime>();

    public Car enterCar(Car car) {
//        carRepository.put(car, LocalTime.now());
        carList.add(car);
        return car;
    }

    public Car getCar(){
//        return carRepository.get(car);
        System.out.println(carList.get(0));
        return carList.get(0);
    }
}

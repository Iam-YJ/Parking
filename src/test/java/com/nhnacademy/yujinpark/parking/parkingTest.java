package com.nhnacademy.yujinpark.parking;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class parkingTest {

    private Car car;
    private CarRepository carRepository;

    @BeforeEach
    void setUp(){
        carRepository = mock(CarRepository.class);
    }

    @DisplayName("주차장에 차가 들어온다")
    // 주차장에 차가 들어온다.
    // 차가 들어오면 번호판을 인식(scan)합니다.
    @Test
    void enter_car_in_parkinglot(){
        String number = "가123";
        Car car = new Car(number);
//        carRepository.enterCar(car);
        when(carRepository.enterCar(car)).thenReturn(car);

        assertThat(car).isNotNull();
        assertThat(car).isEqualTo(car);
    }

    @DisplayName("차가 들어오면 번호판을 인식(scan)합니다")
    @Test
    void scan_car_license(){

    }
}

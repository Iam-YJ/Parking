package com.nhnacademy.yujinpark.parking;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import jdk.swing.interop.SwingInterOpUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class parkingTest {

    private ParkingLot parkingLot;

    @BeforeEach
    void setUp(){
        parkingLot = mock(ParkingLot.class);
    }


    @DisplayName("주차장에 차가 들어온다")
    @Test
    // 주차장에 차가 들어온다
    // 주차장에 저장된 차가 들어온 차가 맞는지
    void enter_car_in_parkinglot(){
        String code = "A-1";
        String number = "A123";

        Car car = new Car(number);

        ParkingSpace parkingSpace = new ParkingSpace(code, car);
        when(parkingLot.enter(parkingSpace)).thenReturn(parkingSpace);

        assertThat(parkingLot.getSpaces()).isNotNull();
        assertThat(parkingLot.checkCarInParkingLot());
    }

    @DisplayName("주차장에 들어온 차가 주차장에 저장된게 맞는지 ")
    @Test
    void check_enter_car_is_equals_in_parkinglot_car(){
        String code = "A-1";
        String number = "A123";

        Car car = new Car(number);
        ParkingSpace parkingSpace = new ParkingSpace(code, car);

        parkingLot.enter(parkingSpace);

        parkingLot.checkCarInParkingLot();



    }

    @DisplayName("차가 들어오면 번호판을 인식(scan)합니다")
    @Test
    void scanning_enter_car_license(){
        ParkingSystem parkingSystem = new ParkingSystem();
        String scanInput = "A123";
        InputStream in = new ByteArrayInputStream(scanInput.getBytes());
        System.setIn(in);
        assertEquals("A123", parkingSystem.scanCarNumber());
    }



    @DisplayName("차를 특정 주차구역에 주차한다")
    @Test
    void check_parking_car_code(){

    }

    @DisplayName("주차장에서 차가 나간다. 차가 잘 나갔는지")
    @Test
    void test2(){

    }

    @DisplayName("나가는 차의 주차 시간만큼 결제를 해야한다")
    @Test
    void test3(){

    }
    



}

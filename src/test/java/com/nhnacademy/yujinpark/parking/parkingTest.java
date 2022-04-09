package com.nhnacademy.yujinpark.parking;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nhnacademy.yujinpark.parking.exception.DoNotAllowCarExitException;
import com.nhnacademy.yujinpark.parking.parkinglot.ParkingLot;
import com.nhnacademy.yujinpark.parking.parkinglot.ParkingSpace;
import com.nhnacademy.yujinpark.parking.subject.Car;
import com.nhnacademy.yujinpark.parking.subject.Money;
import com.nhnacademy.yujinpark.parking.subject.User;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class parkingTest {

    private ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        parkingLot = mock(ParkingLot.class);
    }


    @DisplayName("주차장에 차가 들어온다")
    @Test
        // 주차장에 차가 들어온다
        // 주차장에 저장된 차가 들어온 차가 맞는지
    void enter_car_in_parkinglot() {
        String code = "A-1";
        String number = "A123";

        Car car = new Car(number);

        ParkingSpace parkingSpace = new ParkingSpace(code, car, LocalDateTime.now());
        when(parkingLot.enter(parkingSpace)).thenReturn(parkingSpace);

        assertThat(parkingLot.getSpaces()).isNotNull();
    }

    @DisplayName("주차장에 들어온 차가 주차창 특정 코드에 잘 저장된게 맞는지")
    @Test
    void check_enter_car_is_parked_well() {
        String code1 = "A-1";
        String code2 = "B-1";

        String number1 = "A123";
        String number2 = "B123";

        Car car1 = new Car(number1);
        Car car2 = new Car(number2);
        ParkingSpace parkingSpace1 = new ParkingSpace(code1, car1, LocalDateTime.now());
        ParkingSpace parkingSpace2 = new ParkingSpace(code1, car2, LocalDateTime.now());
        ParkingSpace parkingSpace3 = new ParkingSpace(code2, car1, LocalDateTime.now());
        ParkingSpace parkingSpace4 = new ParkingSpace(code2, car2, LocalDateTime.now());

        ParkingLot parkingLot = new ParkingLot();

        parkingLot.enter(parkingSpace1);

        assertThat(parkingLot.checkCarInParkingLot(parkingSpace1)).isTrue();
        assertThat(parkingLot.checkCarInParkingLot(parkingSpace2)).isFalse();
        assertThat(parkingLot.checkCarInParkingLot(parkingSpace3)).isFalse();
        assertThat(parkingLot.checkCarInParkingLot(parkingSpace4)).isFalse();
    }

    @DisplayName("차가 들어오면 번호판을 인식(scan)합니다")
    @Test
    void scanning_enter_car_license() {
        ParkingLot parkingLot = new ParkingLot();
        String scanInput = "A123";
        InputStream in = new ByteArrayInputStream(scanInput.getBytes());
        System.setIn(in);
        assertEquals("A123", parkingLot.scanCarNumber());
    }

    @DisplayName("주차장에서 차가 나간다. 차가 제대로 나갔는지")
    @Test
    void exit_car_in_parkinglot() {
        String code1 = "A-1";

        String number1 = "A123";
        String number2 = "B123";

        Car car1 = new Car(number1);
        Car car2 = new Car(number2);
        User user = new User("PYJ",new Money(BigDecimal.valueOf(10000)), car1);

        ParkingSpace parkingSpace1 = new ParkingSpace(code1, car1, LocalDateTime.now());
        ParkingSpace parkingSpace2 = new ParkingSpace(code1, car2, LocalDateTime.now());

        ParkingLot parkingLot = new ParkingLot();

        parkingLot.enter(parkingSpace1);
        parkingLot.enter(parkingSpace2); // 차 2대 주차장에 들어옴

        parkingLot.exit(user, parkingSpace1);

        assertThat(parkingLot.checkCarInParkingLot(parkingSpace1)).isFalse();
        assertThat(parkingLot.checkCarInParkingLot(parkingSpace2)).isTrue();
    }

    @DisplayName("나가는 차의 주차 시간 대비 발생한 금액 확인")
    @Test
    void calculate_exit_car_payment_by_parking_time() {
        String code = "A-1";
        String number = "A123";

        Car car = new Car(number);
        // TODO 출차할 때 유저 돈 계산하기 못나가는거도 하기
        ParkingSpace parkingSpace = new ParkingSpace(code, car, LocalDateTime.now());

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.enter(parkingSpace);

        // 29분 주차
        LocalDateTime time = LocalDateTime.now().plusMinutes(29);
        parkingSpace = new ParkingSpace(code, car, time);
        assertThat(parkingLot.calculateExitPay(parkingSpace)).isEqualTo(BigDecimal.valueOf(1000));

        // 30분 주차
        time = LocalDateTime.now().plusMinutes(30);
        parkingSpace = new ParkingSpace(code, car, time);
        assertThat(parkingLot.calculateExitPay(parkingSpace)).isEqualTo(BigDecimal.valueOf(1000));

        // 31분 주차
        time = LocalDateTime.now().plusMinutes(31);
        parkingSpace = new ParkingSpace(code, car, time);
        assertThat(parkingLot.calculateExitPay(parkingSpace)).isEqualTo(BigDecimal.valueOf(1500));

        // 50분 주차
        time = LocalDateTime.now().plusMinutes(50);
        parkingSpace = new ParkingSpace(code, car, time);
        assertThat(parkingLot.calculateExitPay(parkingSpace)).isEqualTo(BigDecimal.valueOf(2000));

        // 61분 주차
        time = LocalDateTime.now().plusMinutes(61);
        parkingSpace = new ParkingSpace(code, car, time);
        assertThat(parkingLot.calculateExitPay(parkingSpace)).isEqualTo(BigDecimal.valueOf(3000));

        // 6시간 주차
        time = LocalDateTime.now().plusHours(6);
        parkingSpace = new ParkingSpace(code, car, time);
        assertThat(parkingLot.calculateExitPay(parkingSpace)).isEqualTo(BigDecimal.valueOf(10000));

        // 1일 주차
        time = LocalDateTime.now().plusHours(24);
        parkingSpace = new ParkingSpace(code, car, time);
        assertThat(parkingLot.calculateExitPay(parkingSpace)).isEqualTo(BigDecimal.valueOf(10000));

        // 1일 1시간 주차
        time = LocalDateTime.now().plusHours(25);
        parkingSpace = new ParkingSpace(code, car, time);
        assertThat(parkingLot.calculateExitPay(parkingSpace)).isEqualTo(BigDecimal.valueOf(13000));

        // 2일 주차
        time = LocalDateTime.now().plusHours(48);
        parkingSpace = new ParkingSpace(code, car, time);
        assertThat(parkingLot.calculateExitPay(parkingSpace)).isEqualTo(BigDecimal.valueOf(20000));
    }

    @DisplayName("나가는 차의 주차요금을 사용자가 계산할 수 있는지(돈이 없으면 차가 나갈 수 없음)")
    @Test
    void calculate_exit_car_payment_by_user_throws_doNotAllowCarExitException() {
        String code = "A-1";
        String number1 = "A123";

        Car car = new Car(number1);
        Money money1 = new Money(BigDecimal.valueOf(0));
        User user1 = new User(number1, money1, car);

        ParkingSpace parkingSpace = new ParkingSpace(code, car, LocalDateTime.now().plusMinutes(30));
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.enter(parkingSpace);

        BigDecimal exitPay = parkingLot.calculateExitPay(parkingSpace); // 1000원

        // 통과해야함
        assertThatThrownBy(() -> parkingLot.calculateParkingPayByUser(user1, exitPay))
            .isInstanceOf(DoNotAllowCarExitException.class)
            .hasMessage("money is not enough to exit parkingLot");

        
        Money money2 = new Money(BigDecimal.valueOf(10000));
        User user2 = new User(number1, money2, car);

        // 통과하지 않아야함
//        assertThatThrownBy(() -> parkingLot.calculateParkingPayByUser(user2, exitPay))
//            .isInstanceOf(DoNotAllowCarExitException.class)
//            .hasMessage("money is not enough to exit parkingLot");
    }

    @DisplayName("주차장 입구가 n개 입니다")
    @Test
    void test(){

    }

}


package com.nhnacademy.yujinpark.parking.payco;

import com.nhnacademy.yujinpark.parking.subject.User;
import java.math.BigDecimal;

public interface PaycoDiscountable {
    BigDecimal discount(User user, BigDecimal exitPay);

    BigDecimal discountByCoupon(User user, BigDecimal exitPay);
}

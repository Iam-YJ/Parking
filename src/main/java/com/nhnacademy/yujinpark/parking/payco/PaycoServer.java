package com.nhnacademy.yujinpark.parking.payco;

import com.nhnacademy.yujinpark.parking.exception.PaycoAuthenticateBarcodeException;

public class PaycoServer {

    public boolean authenticateBarcode(Barcode barcode) {
        if (!barcode.getClass().equals(Barcode.class)) {
            throw new PaycoAuthenticateBarcodeException("barcode");
        }
        return true;
    }

}

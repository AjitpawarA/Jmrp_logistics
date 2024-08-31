package com.alpha.jmrplogistics.exception;

public class CarrierNotFoundException extends RuntimeException {
    public CarrierNotFoundException(String message) {
        super(message);
    }
}
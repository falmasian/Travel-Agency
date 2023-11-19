package com.flight.exception;

public class FailedToPayException extends Throwable {
    public FailedToPayException(String errorMassage){
        super(errorMassage);
    }
}

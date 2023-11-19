package com.flight.exception;

public class EmptyReservationException extends Exception {
    public EmptyReservationException(String errorMassage){
        super(errorMassage);
    }
}

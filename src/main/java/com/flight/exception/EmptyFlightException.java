package com.flight.exception;

public class EmptyFlightException extends Exception {

    public EmptyFlightException (String errorMassage){
        super(errorMassage);
    }
}

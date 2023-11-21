package com.flight.exception;

public class FlightNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String errorMassage;
    public FlightNotFoundException (String errorMassage){
        super(errorMassage);
        this.errorMassage = errorMassage;
    }

    public String getErrorMassage() {
        return errorMassage;
    }
}

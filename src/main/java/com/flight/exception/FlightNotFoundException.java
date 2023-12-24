package com.flight.exception;

import java.io.Serial;

public class FlightNotFoundException extends Exception {

    @Serial
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

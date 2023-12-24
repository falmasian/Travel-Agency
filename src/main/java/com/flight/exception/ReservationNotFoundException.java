package com.flight.exception;

import java.io.Serial;

public class ReservationNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 2L;
    private final String errorMassage;
    public ReservationNotFoundException(String errorMassage){
        super(errorMassage);
        this.errorMassage = errorMassage;
    }

    public String getErrorMassage() {
        return errorMassage;
    }
}

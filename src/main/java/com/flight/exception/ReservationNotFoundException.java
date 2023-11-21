package com.flight.exception;

public class ReservationNotFoundException extends RuntimeException {

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

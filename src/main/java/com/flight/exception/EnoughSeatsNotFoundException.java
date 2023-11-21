package com.flight.exception;

public class EnoughSeatsNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3L;
    private final String errorMassage;
    public EnoughSeatsNotFoundException(String errorMassage) {
        super(errorMassage);
        this.errorMassage = errorMassage;
    }

    public String getErrorMassage() {
        return errorMassage;
    }
}

package com.flight.exception;

public class InvalidInputException extends RuntimeException {

    private static final long serialVersionUID = 5L;
    private final String errorMassage;

    public InvalidInputException(String errorMassage) {
        super(errorMassage);
        this.errorMassage = errorMassage;
    }

    public String getErrorMassage() {
        return errorMassage;
    }
}

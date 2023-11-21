package com.flight.exception;

public class FailedToPayException extends RuntimeException {


    private static final long serialVersionUID = 4L;
    private final String errorMassage;
    public FailedToPayException(String errorMassage){
        super(errorMassage);
        this.errorMassage = errorMassage;
    }

    public String getErrorMassage() {
        return errorMassage;
    }
}

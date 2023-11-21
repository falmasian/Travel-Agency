package com.flight.exception;

import java.io.Serial;

public class FailedToPayException extends RuntimeException {


    @Serial
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

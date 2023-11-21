package com.flight.exception;

import java.io.Serial;

public class EnoughSeatsNotFoundException extends RuntimeException {

    @Serial
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

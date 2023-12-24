package com.flight.exception;

import java.io.Serial;

public class InsufficientSeatsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3L;
    private final String errorMassage;
    public InsufficientSeatsException(String errorMassage) {
        super(errorMassage);
        this.errorMassage = errorMassage;
    }

    public String getErrorMassage() {
        return errorMassage;
    }
}

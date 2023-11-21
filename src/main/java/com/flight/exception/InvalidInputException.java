package com.flight.exception;

import java.io.Serial;

public class InvalidInputException extends RuntimeException {

    @Serial
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

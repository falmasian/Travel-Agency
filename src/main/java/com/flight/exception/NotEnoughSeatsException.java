package com.flight.exception;

public class NotEnoughSeatsException extends Exception {
    public NotEnoughSeatsException(String errorMassage)
    {
        super(errorMassage);
    }
}

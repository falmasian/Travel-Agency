package com.flight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FlightAgencyException {

    @ExceptionHandler(value = FlightNotFoundException.class)
    public ResponseEntity<Object> exception(FlightNotFoundException exception) {
        return new ResponseEntity<>(exception.getErrorMassage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ReservationNotFoundException.class)
    public ResponseEntity<Object> exception(ReservationNotFoundException exception) {
        return new ResponseEntity<>(exception.getErrorMassage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EnoughSeatsNotFoundException.class)
    public ResponseEntity<Object> exception(EnoughSeatsNotFoundException exception) {
        return new ResponseEntity<>(exception.getErrorMassage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = FailedToPayException.class)
    public ResponseEntity<Object> exception(FailedToPayException exception) {
        return new ResponseEntity<>(exception.getErrorMassage(), HttpStatus.NOT_MODIFIED);
    }

    @ExceptionHandler(value = InvalidInputException.class)
    public ResponseEntity<Object> exception(InvalidInputException exception) {
        return new ResponseEntity<>(exception.getErrorMassage(), HttpStatus.BAD_REQUEST);
    }

}

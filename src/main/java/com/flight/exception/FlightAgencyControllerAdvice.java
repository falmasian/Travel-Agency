package com.flight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FlightAgencyControllerAdvice {

//    @ExceptionHandler(value = FlightAgencyBaseException.class)
//    public ResponseEntity<Object> exception(FlightAgencyBaseException exception) {
//        return new ResponseEntity<>(exception.getErrorCode(), HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(value = FlightNotFoundException.class)
    public ResponseEntity<Object> exception(FlightNotFoundException exception) {
        return new ResponseEntity<>(exception.getErrorMassage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ReservationNotFoundException.class)
    public ResponseEntity<Object> exception(ReservationNotFoundException exception) {
        return new ResponseEntity<>(exception.getErrorMassage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InsufficientSeatsException.class)
    public ResponseEntity<Object> exception(InsufficientSeatsException exception) {
        return new ResponseEntity<>(exception.getErrorMassage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PaymentProblemException.class)
    public ResponseEntity<Object> exception(PaymentProblemException exception) {
        return new ResponseEntity<>(exception.getErrorMassage(), HttpStatus.NOT_MODIFIED);
    }

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<Object> exception(ValidationException exception) {
        return new ResponseEntity<>(exception.getErrorMassage(), HttpStatus.BAD_REQUEST);
    }

}

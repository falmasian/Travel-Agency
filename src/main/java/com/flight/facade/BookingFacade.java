package com.flight.facade;

import com.flight.dto.*;
import com.flight.exception.FlightNotFoundException;
import com.flight.exception.ReservationNotFoundException;
import com.flight.exception.InsufficientSeatsException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface BookingFacade {

    String baseUrl = "/book";
    /**
     * @param bookingDto
     * @return tracing code
     * عملیات رزرو  را انجام میدهد و کد پیگیری را برمیگرداند
     */
    @PostMapping(value = baseUrl + "/reserve", consumes = MediaType.APPLICATION_JSON_VALUE)
    ReservationResponseDto reserve(@RequestBody BookingDto bookingDto)
            throws FlightNotFoundException, InsufficientSeatsException;

    /**
     * @param filterFlightDto پرواز ورودی
     * @return List<FlightDto>
     *      همه پرواز هایی که مبدا و مقصد زمانشان با پپرواز ورودی مطابقت داشته باشد را فیلتر میکند و نشان میدهد
     */
    @PostMapping(value = baseUrl + "/filter", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
   FilterResponseDto filter(@RequestBody FilterFlightDto filterFlightDto) throws FlightNotFoundException;

    /**
     * @param cancellationDto مشخصات ورودی
     * @return cost
     * کنسل کردن بلیط های رزرو شده را انجام میدهد و هزینه  ای که باید به خریدار برگردانده شود را نشان میدهد
     */
    @PostMapping(value = baseUrl + "/cancel", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    CancellingResponseDto cancel(@RequestBody CancellationDto cancellationDto) throws FlightNotFoundException
            , ReservationNotFoundException;

    /**
     * @param reservationDto مشخصات ورودی
     * @return List<ReservationGetDto>
     *     همه ی رزرو های مشتری را نشان میدهد
     */
    @PostMapping(value = baseUrl + "/all-customer-reservations", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerReservationsResponseDto getAllReservations(@RequestBody ReservationDto reservationDto)
            throws ReservationNotFoundException;
}

package com.flightagency.controller;

import com.flightagency.dto.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Component
public interface BookingController {

    /**
     * @param bookingDto
     * @return tracing code
     * عملیات رزرو  را انجام میدهد و کد پیگیری را برمیگرداند
     */
    @PostMapping(value = "/api/book/reserve", consumes = MediaType.APPLICATION_JSON_VALUE)
    String book(@RequestBody BookingDto bookingDto);


    /**
     * @param filterFlightDto
     * @return List<FlightDto>
     *      همه پرواز هایی که مبدا و مقصد زمانشان با پپرواز ورودی مطابقت داشته باشد را فیلتر میکند و نشان میدهد
     */
    @PostMapping(value = "/api/book/filter", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    List<FlightDto> filter(@RequestBody FilterFlightDto filterFlightDto);

    /**
     * @param cancellationDto
     * @return cost
     * کنسل کردن بلیط های رزرو شده را انجام میدهد و هزینه  ای که باید به خریدار برگردانده شود را نشان میدهد
     */
    @PostMapping(value = "/api/book/cancel", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    float cancelling(@RequestBody CancellationDto cancellationDto);

    /**
     * @param reservationDto
     * @return List<ReservationGetDto>
     *     همه ی رزرو های مشتری را نشان میدهد
     */
    @PostMapping(value = "/api/book/getAllCustomerReservations", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<ReservationGetDto> getAllReservations(@RequestBody ReservationDto reservationDto);
}

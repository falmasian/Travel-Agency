package com.flight.facade;

import com.flight.dto.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Component
public interface BookingFacade {

    String baseUrl = "/book/";
    /**
     * @param bookingDto
     * @return tracing code
     * عملیات رزرو  را انجام میدهد و کد پیگیری را برمیگرداند
     */
    @PostMapping(value = baseUrl + "reserve", consumes = MediaType.APPLICATION_JSON_VALUE)
    String reserve(@RequestBody BookingDto bookingDto);


    /**
     * @param filterFlightDto پرواز ورودی
     * @return List<FlightDto>
     *      همه پرواز هایی که مبدا و مقصد زمانشان با پپرواز ورودی مطابقت داشته باشد را فیلتر میکند و نشان میدهد
     */
    @PostMapping(value = baseUrl + "filter", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    List<FlightDto> filter(@RequestBody FilterFlightDto filterFlightDto);

    /**
     * @param cancellationDto مشخصات ورودی
     * @return cost
     * کنسل کردن بلیط های رزرو شده را انجام میدهد و هزینه  ای که باید به خریدار برگردانده شود را نشان میدهد
     */
    @PostMapping(value = baseUrl + "cancel", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    float cancel(@RequestBody CancellationDto cancellationDto);

    /**
     * @param reservationDto مشخصات ورودی
     * @return List<ReservationGetDto>
     *     همه ی رزرو های مشتری را نشان میدهد
     */
    @PostMapping(value = baseUrl + "getAllCustomerReservations", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<ReservationGetDto> getAllReservations(@RequestBody ReservationDto reservationDto);
}

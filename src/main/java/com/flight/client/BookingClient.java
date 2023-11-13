package com.flight.client;

import com.flight.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class BookingClient {


    private final RestTemplate restTemplate;
    @Value("${rest.client.base-url}")
    private String BASE_URL;

    @Autowired
    public BookingClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void bookingProcess() {
        if (filterFlights()) {
            String tracingCode = book();
            if (tracingCode != null && tracingCode.equals("-1")) {
                System.out.println("Booking failed. Probably the flight number was wrong.");
                return;
            }
            if (tracingCode != null) {
                continueBooking(tracingCode);
            } else {
                System.out.println("Booking failed.");
            }
        }
    }

    public boolean filterFlights() {
        String cityUrl = BASE_URL + "/api/city/getAll";
        ParameterizedTypeReference<List<CityDto>> cityResponseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<CityDto>> cityResponse = restTemplate.exchange(cityUrl
                , HttpMethod.GET, null, cityResponseType);
        List<CityDto> cityDtoList = cityResponse.getBody();
        if (cityDtoList == null) {
            return false;
        }
        int i = 0;
        for (CityDto c : cityDtoList) {
            i++;
            System.out.println(i + ". " + c.getCityName());
        }
        FilterFlightDto filterFlightDto = getInput();
        String filterUrl = BASE_URL + "/api/book/filter";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FilterFlightDto> requestEntity = new HttpEntity<>(filterFlightDto, headers);
        ParameterizedTypeReference<List<FlightDto>> filterResponseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<FlightDto>> filterResponse = restTemplate.exchange(filterUrl
                , HttpMethod.POST, requestEntity, filterResponseType);

        List<FlightDto> flightDtoList = filterResponse.getBody();
        if (flightDtoList == null || flightDtoList.size() <= 0) {
            System.out.println("No match flights with this specification was found");
            return false;
        }
        System.out.println("flight number	origin code	destination code  flight date	cost   remaining seats");
        for (FlightDto f : flightDtoList) {
            System.out.println(f.getFlightNumber() + "		"
                               + f.getOriginId() + "   		"
                               + f.getDestinationId() + "  	 	 "
                               + f.getFlyDateTime() + " 	 "
                               + f.getCost() + "   "
                               + f.getRemainingSeats());
        }
        return flightDtoList.size() > 0;
    }

    public FilterFlightDto getInput() {
        Scanner s = new Scanner(System.in);

        System.out.println("enter origin city number");
        int originId = s.nextInt();
        System.out.println("enter destination city number");
        int destinationId = s.nextInt();
        System.out.println("enter flight date in this format: YYYY-MM-DD");
        String str = s.next();
        Date flightDate = java.sql.Date.valueOf(str);

        return new FilterFlightDto(originId, destinationId, flightDate);
    }

    public String book() {
        Scanner s = new Scanner(System.in);
        System.out.println("enter Flight number");
        int flightId = s.nextInt();
        System.out.println("enter your national code");
        Scanner s1 = new Scanner(System.in);
        String customerId = s1.nextLine();
        while (customerId.trim().length() != 10) {
            System.out.println("national code must have 10 character.please try again.");
            System.out.println("enter your national code");
            Scanner s2 = new Scanner(System.in);
            customerId = s1.nextLine();
        }
        System.out.println("how many tickets do you want?");
        int numberOfTickets = s.nextInt();
        ArrayList<String> nationalcodes = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            int j = i + 1;
            System.out.println("enter national code of passenger number " + j + " ");
            Scanner scanner = new Scanner(System.in);
            String nationalCode = scanner.nextLine().trim();
            if (nationalcodes.contains(nationalCode)) {
                i--;
                System.out.println("duplicate national code! please try again.");
                continue;
            }
            if (nationalCode.trim().length() != 10) {
                i--;
                System.out.println("national code must have 10 character.please try again.");
            }

            nationalcodes.add(nationalCode);

        }
        BookingDto bookingDto = new BookingDto(customerId, flightId, nationalcodes);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<BookingDto> requestEntity = new HttpEntity<>(bookingDto, headers);
        String url = BASE_URL + "/api/book/reserve";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return response.getBody();
    }

    public void continueBooking(String tracingCode) {
        Scanner s = new Scanner(System.in);
        System.out.println("The temporary reservation has been made.\n your tracking code is: " + tracingCode
                           + " \n You have ten minutes to pay and finalize your request");
        System.out.println("do you want to pay now? 1.yes 2.no");
        String response = s.nextLine();
        if (response.trim().equals("1")) {
            paymentProcess(tracingCode);
        } else {
            System.out.println("we will wait until 10 minutes later!");
        }
    }

    public void getAllFlights() {
        String endpoint = "/api/flight/getAll";
        String url = BASE_URL + endpoint;
        ParameterizedTypeReference<List<FlightDto>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<FlightDto>> response = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        List<FlightDto> flightDtoList = response.getBody();
        if (flightDtoList == null) {
            return;
        }
        System.out.println("flight number	origin code	destination code  flight date	cost   remaining seats");
        for (FlightDto f : flightDtoList) {
            System.out.println(f.getFlightNumber() + "	"
                               + f.getOriginId() + " 	"
                               + f.getDestinationId() + "  	  "
                               + f.getFlyDateTime() + " 	 "
                               + f.getCost() + "   "
                               + f.getRemainingSeats());
        }
    }

    public void cancellingProcess() {
        List<ReservationGetDto> reservationGetDtoList = getAllClientReservations();
        if (reservationGetDtoList.size() <= 0) {
            return;
        }
        System.out.println("you have reserved " + reservationGetDtoList.size()
                           + " seats with this tracking code."
                           + " \nHow many do you want to cancel?");

        Scanner ss = new Scanner(System.in);
        int numberOfTickets = ss.nextInt();
        if (numberOfTickets > reservationGetDtoList.size()) {
            System.out.println("this input is more than your reserved seats.");
            return;
        }
        if (numberOfTickets <= 0) {
            System.out.println("invalid input");
            return;
        }
        String customerId = reservationGetDtoList.get(0).getCustomerId();
        ArrayList<String> nationalcodes = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            int j = i + 1;
            System.out.println("enter passanger number " + j + " nationalcode: ");
            Scanner scanner1 = new Scanner(System.in);
            String code = scanner1.nextLine().trim();
            int numOfTicketsWithInputNationalCode = reservationGetDtoList
                    .stream()
                    .filter(c -> c.getNationalCode()
                            .equals(code.trim()))
                    .toList()
                    .size();
            if (numOfTicketsWithInputNationalCode > 0) {
                nationalcodes.add(code);
                System.out.println(nationalcodes.contains(code));
            } else {
                System.out.println("You do not have a reservation with this national code.please enter again.");
                i--;
            }
        }
        CancellationDto cancellationDto = new CancellationDto(customerId, nationalcodes);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CancellationDto> requestEntity = new HttpEntity<>(cancellationDto, headers);
        String url = BASE_URL + "/api/book/cancel";
        ResponseEntity<Float> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Float.class);
        Float cost = response.getBody();

        if (cost != null && cost > 0) {
            System.out.println("The tickets have been successfully cancelled.\n"
                               + cost + "$ will be deposited into your account within twenty-four hours");
        } else {
            System.out.println("Could not able to cancel.");
        }
    }

    public List<ReservationGetDto> getAllClientReservations() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your national code: ");
        String nationalCode = scanner.nextLine().trim();
        ReservationDto reservationDto = new ReservationDto(nationalCode);
        String url = BASE_URL + "/api/book/getAllCustomerReservations";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ReservationDto> requestEntity = new HttpEntity<>(reservationDto, headers);
        ParameterizedTypeReference<List<ReservationGetDto>> responseType = new ParameterizedTypeReference<>() {
        };

        ResponseEntity<List<ReservationGetDto>> response = restTemplate.exchange(url, HttpMethod.POST,
                requestEntity, responseType);

        List<ReservationGetDto> reservationGetDtoList = response.getBody();

        if (reservationGetDtoList == null || reservationGetDtoList.size() <= 0) {
            System.out.println("there is no reservation with this national code.");

        } else {
            System.out.println("customerId   flight number   passenger nationalCode  tracingCode");
            for (ReservationGetDto r : reservationGetDtoList) {
                System.out.println(r.getCustomerId() + "    " + r.getFlightId() + "    " + r.getNationalCode()
                                   + "    " + r.getTrackingCode());
            }
        }
        return reservationGetDtoList;
    }

    public void paymentProcess() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter your tracking code: ");
            String str = scanner.nextLine();
            this.paymentProcess(str.trim());
        } catch (Exception ex) {
            System.out.println("Could not pay now.please try again");
        }
    }

    public void paymentProcess(String tracingCode) {
        PaymentDto paymentDto = new PaymentDto(tracingCode);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentDto> requestEntity = new HttpEntity<>(paymentDto, headers);
        String url = BASE_URL + "/api/payment";
        ResponseEntity<Float> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Float.class);
        Float cost = response.getBody();
        if (cost == null) {
            System.out.println("tracking code did not find or Your payment deadline has expired.");

        } else if (cost > 0) {
            System.out.println("You have successfully paid. Your flight has been confirmed");
            System.out.println(cost + "$ Deducted from your account");
        } else if (cost == -1) {
            System.out.println("sorry!There are not enough seats left.");
        } else if (cost < -1) {
            System.out.println("tracking code did not find or Your payment deadline has expired.");
        }
    }
}

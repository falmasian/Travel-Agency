package com.flightagency.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Client {

    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    BookingClient bookingClient;
    PaymentClient paymentClient;

    public Client(BookingClient bookingClient, PaymentClient paymentClient) {
        this.bookingClient = bookingClient;
        this.paymentClient = paymentClient;
    }

    public int showMenu() {
        String menu = """
                what do you want to do?
                1. Book Flight
                2. Cancel Reservation 
                3. pay one of my reservation
                4. see my reservations 
                5. see all flights
                6. filter Flights """;
        try {
            System.out.println(menu);
            int item = scanner.nextInt();
            return item;
        } catch (Exception ex) {
            logger.error("Error in the server ");
        }
        return 0;
    }

    public void selectingItem(int item) {
        if (item == 1) {
            bookingClient.bookingProcess();
        } else if (item == 2) {
            bookingClient.cancellingProcess();
        } else if (item == 3) {
            bookingClient.paymentProcess();
        } else if (item == 4) {
            bookingClient.getAllClientReservations();
        } else if (item == 5) {
            bookingClient.getAllFlights();
        } else if (item == 6) {
            bookingClient.filterFlights();
        }
        process();
    }

    public void process() {
        int selectedItem = showMenu();
        selectingItem(selectedItem);
    }
}
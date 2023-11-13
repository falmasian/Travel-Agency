package com.flight.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Client {

    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    BookingClient bookingClient;

    public Client(BookingClient bookingClient) {
        this.bookingClient = bookingClient;
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
        switch (item) {
            case 1 -> bookingClient.bookingProcess();
            case 2 -> bookingClient.cancellingProcess();
            case 3 -> bookingClient.paymentProcess();
            case 4 -> bookingClient.getAllClientReservations();
            case 5 -> bookingClient.getAllFlights();
            case 6 -> bookingClient.filterFlights();
            default -> showMenu();
        }
        process();
    }

    public void process() {
        int selectedItem = showMenu();
        selectingItem(selectedItem);
    }
}
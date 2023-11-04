package com.flightagency.client;

import com.flightagency.controller.PaymentController;
import com.flightagency.dto.PaymentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class PaymentClient {
    private static final Logger logger = LoggerFactory.getLogger(PaymentClient.class);
    PaymentController paymentController;
    Scanner scanner = new Scanner(System.in);

    public PaymentClient(PaymentController paymentController) {
        this.paymentController = paymentController;
    }

    public void process() {
        try {
            System.out.println("Enter your tracking code: ");
            String str = scanner.nextLine();
            this.process(str.trim());
        } catch (Exception ex) {
            logger.error("Error in the server");
        }
    }

    public void process(String tracingCode) {
        PaymentDto paymentDto = new PaymentDto(tracingCode);
        float cost = paymentController.payment(paymentDto);
        if (cost > 0) {
            System.out.println("You have successfully paid. Your flight has been confirmed");
            System.out.println(cost + "$ Deducted from your account");
        } else if (cost == -1) {
            System.out.println("sorry!There are not enough seats left.");
        } else if (cost < -1) {
            System.out.println("tracking code did not find or Your payment deadline has expired.");
        }
    }
}

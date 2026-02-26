package com.miniecommerce.order_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendOrderConfirmation(String to,
                                      String productName,
                                      Integer quantity,
                                      Double price) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Order Confirmation");

        String body = """
                Thank you for your order!

                Product: %s
                Quantity: %d
                Price: %.2f
                Expected Arrival: Soon

                We appreciate your purchase.
                """.formatted(productName, quantity, price);

        message.setText(body);

        mailSender.send(message);
    }
}